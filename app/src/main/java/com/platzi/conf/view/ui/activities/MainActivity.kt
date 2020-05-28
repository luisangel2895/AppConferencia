package com.platzi.conf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.firestore.FirebaseFirestore
import com.platzi.conf.R
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recordar q nuestro app no tiene toolbar por defecto y nosotros creamos uno manualmente
        //aca agregamos a la vista principal ese toolbar q creamos manualmente en activity_main
        setActionBar(findViewById(R.id.toolbarMain))

        //creamos la funcion confignav
        configNav()

    }

    //en esta funcion activamos el sistema de navegacion q creamos
    private fun configNav() {
        // le indicamos q este sistema de navegacion es por el bnvmemu, y luego el activity host q es el fragment de activity_main
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent))
    }

}
