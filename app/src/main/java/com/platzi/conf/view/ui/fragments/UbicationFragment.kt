package com.platzi.conf.view.ui.fragments


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.platzi.conf.R
import com.platzi.conf.model.Ubication

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {//implementamos primero en onmap para configurar el mapa y google.onmackclick para poder controlar los clicks en el mapa
                        //y al hacer estas implementacion se deben crear las fucnion onMapReady y onMarkerClick

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    //usamos la funcion de viewmodel para controlar el ciclo de vida del viewmodel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //con estas 2 lineas cargamos el mapa en el framento
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment // primero ubicamos el fragmento donde colocaremos el mapa con childFragmentManager y mediante su id: map

        mapFragment.getMapAsync(this)// y luego lo cargamos
    }

    //en esta funcion acercaremos el mapa a la ubicacion de la conferencia y pondremos el logo
    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication()// primero llamamos a nuestro objeto ubicacion q ya llenamos en los modelos con los datos de nuestra ubicacion

        val zoom = 16f// definimos la variable zoom del mapa en google varia entre 1f a 19f, 16f es aceptable en este caso
        val centerMap = LatLng(ubication.latitude, ubication.longitude)// definimos en un objeto especial de google la ubicacion en latitud y longitud

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))//ahora colocamos esta ubicacion en el mapa con el zoom

        //ahora creamos el icono de platzi para el lugar
        val centerMark = LatLng(ubication.latitude, ubication.longitude)//igualmente primero creamos un objeto especial para la ubicacion del icono q es la misma q del lugar
        val markerOptions = MarkerOptions()//creamos un texto como marca para la posicion
        markerOptions.position(centerMark)//colocamos el texto en el sitio
        markerOptions.title("Platzi Conf 2019")//y definimos q le colocamos de texto

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable //traemos el icono de platzi para el mapa, la imagen tiene q ser de tipo BitmapDraw por eso lo parseamos
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)// le damos tamano al icono

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))//al marcador q creamos le agregamos este icono

        googleMap?.addMarker(markerOptions)//finalmente ponemos el marcador en el mapa


        googleMap?.setOnMarkerClickListener(this)//hacemos q google maps este atento a los click del marcador en el mapa

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))// por ultimo le agregamos los estilos a nuestro mapa del json q descargamos


    }
    //en esta funcion se ejecuta la accion cuando se hace click en la marca en el mapa
    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)// hacemos q nos  mande a ubicationFragmentDialog q lo creamos analogo a los otros DialogFragment
        return true
    }

}
