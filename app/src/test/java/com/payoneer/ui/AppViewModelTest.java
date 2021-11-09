package com.payoneer.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.payoneer.data.AppRepository;
import com.payoneer.data.PayoneerApi;
import com.payoneer.model.ApplicableNetwork;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.Assert.*;



public class AppViewModelTest {

    private AppViewModel appViewModel;
    private AppRepository appRepo;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private PayoneerApi mockApi;

    @Mock
    Observer<List<ApplicableNetwork>> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        appRepo = new AppRepository(mockApi);
        appViewModel = new AppViewModel(appRepo);

    }

    @Test
    public void testObjectsInitializedOnCreation() {
        appViewModel.getPaymentOptionLiveData().observeForever(observer);
        assertNotNull( appViewModel.getPaymentOptionLiveData());
        assertTrue(appViewModel.getPaymentOptionLiveData().hasObservers());
    }

    @After
    public void tearDown(){
        appViewModel = null;
        appRepo = null;
    }


}