package com.platzi.conf.view.ui.fragments


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.platzi.conf.R
import com.platzi.conf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment(){ //lo hacemos heredar de dialogfragment ya q es un dialogfragment xd
    //sobreescribimos la funcion oncreate para ponerle un estilo propio a los dialogos del cronograma
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)//y aca  le agregamos el stylo q le creamos a este dialogo
    }

    //creada por defecto
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    //creamos la clase para tener control del viewmodel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ahora configuramos el toolbar del dialogfragment
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)// le colocamos una x
        toolbarConference.setTitleTextColor(Color.WHITE)// de color blanco
        toolbarConference.setNavigationOnClickListener {// y cuando alguien le haga click este dialog fragent se cierre
            dismiss()
        }

        //ahora recibimos el objeto serializable q enviamos desde el fragment con nombre "conference"
        val conference = arguments?.getSerializable("conference") as Conference

        toolbarConference.title = conference.title// con esto hacemos q en el toobar este el titulo de la conferencia

        //ahora colocamos lo recibido en el view del detail dialog con los ids de sus views
        tvItemScheduleTituloConferencia.text = conference.title
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description
        //ahora con esto no esta completo ya q por defecto un dialogo tiene caracteristicas de diseno como
        //color de fondo transparente, etc entonces para esto vamos a sytles.xml y creamos un estilo para los dialos llamado FullScreenDialogStyle
    }

    // ademos sobreescribimos la funcion onstart para q este dialogo ocupe toda la pantalla
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
