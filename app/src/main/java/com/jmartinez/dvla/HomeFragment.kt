package com.jmartinez.dvla

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jmartinez.dvla.base.ViewModelFactory
import com.jmartinez.dvla.databinding.FragmentHomeBinding
import com.jmartinez.dvla.network.RemoteDataSource
import com.jmartinez.dvla.network.Resource
import com.jmartinez.dvla.network.VehicleRegistrationApi
import com.jmartinez.dvla.repository.VehicleRegistrationRepository
import com.jmartinez.dvla.response.RegistrationNumber
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding
    protected val remoteDataSource = RemoteDataSource()
    private lateinit var EtRegisterNumber:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val factory = ViewModelFactory(VehicleRegistrationRepository(remoteDataSource.buildApi(VehicleRegistrationApi::class.java)))

        viewModel = ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         EtRegisterNumber = view.findViewById<EditText>(R.id.EtRegisterNumber)
        val TvRegistrationNumberTitle = view.findViewById<TextView>(R.id.TvRegistrationNumberTitle)

        viewModel.vehicleRegistrationResponse.observe(viewLifecycleOwner, Observer {

            when (it){

                is Resource.Success->{
                    lifecycleScope.launch {
                       // requireActivity().startNewActivity(InfoActivity::class.java)

                    }

                }

                is Resource.Failure ->handleApiError(it){
                    Toast.makeText(context, it.errorCode.toString(), Toast.LENGTH_SHORT).show()

                    getVehicleRegistration()


                }
                else -> {

                }
            }
        })

        EtRegisterNumber.setOnEditorActionListener { _, keyEvent,_ ->
            if (keyEvent== EditorInfo.IME_ACTION_DONE){
                getVehicleRegistration()

            }
            false
        }

        TvRegistrationNumberTitle.setOnClickListener {
            Toast.makeText(context, "iuh", Toast.LENGTH_SHORT).show()

        }







    }

    private fun getVehicleRegistration() {
        val registrationNumber = EtRegisterNumber.text.toString().trim()
        val _registrationNumber = RegistrationNumber(registrationNumber)
        viewModel.getVehicleRegistrationResponse(/*_registrationNumber*/)
    }




}