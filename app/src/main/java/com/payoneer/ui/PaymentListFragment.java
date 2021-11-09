package com.payoneer.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.payoneer.R;
import com.payoneer.adapter.PaymentListResultsAdapter;
import com.payoneer.databinding.FragmentPaymentListBinding;
import com.payoneer.model.ApplicableNetwork;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
/**
 * Contains the RecyclerView and response error handling logic
 */
@AndroidEntryPoint
public class PaymentListFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener {
    /** UI element binding */
    private FragmentPaymentListBinding binding;

    /** ViewModel to fetch data */
    private AppViewModel appViewModel;

    /** Adapter to pass response into RecyclerView */
    private PaymentListResultsAdapter paymentListResultsAdapter;

    /** implements LiveData observer and response logic */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        paymentListResultsAdapter = new PaymentListResultsAdapter();
        appViewModel.getPaymentOptionLiveData().observe(this, new Observer<List<ApplicableNetwork>>() {
            @Override
            public void onChanged(List<ApplicableNetwork> applicableNetworks) {
                binding.refreshLayout.setRefreshing(false);
                if(applicableNetworks != null) {
                    paymentListResultsAdapter.setQueryResults(applicableNetworks);
                }else{
                    Snackbar.make(binding.getRoot(),getString(R.string.netwok_error),Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    /** define UI behavior */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_paymentListFragment_to_homeFragment);
            }
        });
        binding.networkList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.networkList.setAdapter(paymentListResultsAdapter);
        binding.refreshLayout.setOnRefreshListener(this);
        binding.refreshLayout.setColorSchemeResources(
                R.color.payoneer_orange,
                R.color.payoneer_red
        );
        binding.refreshLayout.setRefreshing(true);
        appViewModel.loadPaymentOptions();
        return view;
    }

    /** recall Api on failure */
    @Override
    public void onRefresh() {
        binding.refreshLayout.setRefreshing(true);
        appViewModel.loadPaymentOptions();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}