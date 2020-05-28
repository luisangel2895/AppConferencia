package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.model.Conference
import com.platzi.conf.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//le enviamos a la calse como parametro la inerfaz de schedule listener q usaremos luego para el click y asi ver el detalle del cronograma
class ScheduleAdapter(val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    //al crear un recicler view por codigo se crean estas 3 funciones por defecto
    var listConference = ArrayList<Conference>()

    //esta funcion definimos cual es la vista de cada elemento del recicler view en este caso es item_schedule, esta vista item se guarda como viewholder para este adaptador
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    //en esta funcion le indicamos el numero de elementos de nuestro recicler view
    override fun getItemCount() = listConference.size

    //esta funcion creada por defecto recibe por defecto el viewholder de la otra funcion como holder y la posicion de cada item holder con int
    //esta es la funcion final ya q se conoce el enlace con los views ahora hacemos el enlace con los datos en esta funcion
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateformat = SimpleDateFormat("HH:mm")
        val simpleDateformatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateformat.format(conference.datetime)

        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateformatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {// implementamos la interfaz mandando la conferencia y la posicion del click esto para el detalle
            scheduleListener.onConferenceClicked(conference, position)
        }

    }
    //creamos un metodo para llenar la lista de conferencias de nuestro adaptador esto lo llenaremos desde otra funcion
    fun updateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }
    //tambien tenemos q crear una clase interna con el nombre viewholder q representa cada item y realizar el enlace solo con los views
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }

}