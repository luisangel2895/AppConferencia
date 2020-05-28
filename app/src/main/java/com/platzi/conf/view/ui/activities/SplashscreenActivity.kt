package com.platzi.conf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.platzi.conf.R
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        //con esto indico q el logo realice la animacion inicial q creamos en la carpeta anim llamda animacion.xml
        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        ivLogoPlatziConf.startAnimation(animacion)

        val intent = Intent(this, MainActivity::class.java) // definimos el main activity como intent

        //creamos un listener de la animacion q crea sus 3 funciones internas
        animacion.setAnimationListener(object : Animation.AnimationListener {
            //en esta funcion estara lo q queremos q pase cuando la animacion se este repitiendo
            override fun onAnimationRepeat(animation: Animation?) {

            }
            //en esta fucnion estara lo q queremos q paso cuando termine la animacion
            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)//colocamos q al terminar la animacion inicie la activity MainActivity q lo definimos como intent
                finish()//con esto destruimos el activity de la animacion
            }
            //en esta funcion esta lo q queremos q pase cuando inicie la animacion
            override fun onAnimationStart(animation: Animation?) {

            }
        })

    }
}
