package com.platzi.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.platzi.conf.R
import com.platzi.conf.model.Conference
import com.platzi.conf.view.adapter.ScheduleAdapter
import com.platzi.conf.view.adapter.ScheduleListener
import com.platzi.conf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(), ScheduleListener {// implementamos la interfaz schedulelistener para el click q envie al detalle
    //primero creamos 2 variables de tipo viewmodel(controlador) y del adaptador
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    //con esta funcion controlamos el ciclo de vida del viewmodel
    //para esto primero agergarmos las 2 dependencias de viewmodel en gradle
    //recordar q nuestros viewmodel deben de heredar de la clase VieModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ahora llenamos nuestra variale de viewmodel con el  viewmodel del cronograma
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        // ahora el viewmodel del cronograma hacemos q ejecute la funcion de refresh para q traiga los datos de firebase
        viewModel.refresh()

        //ahora llenamos nuestra variable de adaptador con el adaptador del cronograma
        scheduleAdapter = ScheduleAdapter(this)

        //ahora vamos al id de nuestro recicler view q esta en fragment_schedule y lo enlazamos con su adaptador
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)// le colocamos algunas confuraciones como q muestre los items lineal y vertical,etc
            adapter = scheduleAdapter // aca colocamos el adaptador del recicler
        }
        observeViewModel()//luego creamos una funcion observador q actualice los datos cuando hayan
    }
    //esta funcion q creamos se encargara de q este viewmodel tenga livedata
    fun observeViewModel() {
        viewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)//con esto cda vez q haya un nuevo datoa se actualizara de manera automatica
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if(it != null)//aca con ayuda de la variable isloading del viewmodel controlamos el progresbar
                rlBaseSchedule.visibility = View.INVISIBLE //con esto hacemos q si isloading es verdad no se muestre el recicler view de fondo y se haga invisible
        })
    }

    //tambien implementamos la funcion para cuando se haga click en una conferencia del cronograma
    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference) // con esto guardamos la conferencia donde hicimos click en una variable
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)// y luego la mandamos al detalle del cronograma
    }

}
