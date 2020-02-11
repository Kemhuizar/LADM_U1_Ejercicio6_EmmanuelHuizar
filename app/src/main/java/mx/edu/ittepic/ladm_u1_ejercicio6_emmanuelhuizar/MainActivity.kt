package mx.edu.ittepic.ladm_u1_ejercicio6_emmanuelhuizar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            guardarArchivoInterno()
        }

        button2.setOnClickListener {
            leerArchivoInterno()
        }
    }

    fun guardarArchivoInterno(){
        try{
            var flujoSalida=OutputStreamWriter(openFileOutput("archivo.txt", Context.MODE_PRIVATE))
            var data=editText.text.toString()+"&"+editText2.text.toString()+"&"+editText3.text.toString()

            flujoSalida.write(data)
            flujoSalida.flush()
            flujoSalida.close()

            mensaje("Exito! el archivo se guardo correctamente")
            ponerTexto("","","")

        }catch(error:IOException){
            mensaje(error.message.toString())
        }
    }

    private fun leerArchivoInterno(){
        try{
            var flujoEntrada=BufferedReader(InputStreamReader(openFileInput("archivo.txt")))


            var data=flujoEntrada.readLine()
            var vector=data.split("&")

            ponerTexto(vector[0],vector[1],vector[2])
            flujoEntrada.close()

        }catch (error:IOException){
            mensaje(error.message.toString())
        }
    }

    fun mensaje(m:String){
        AlertDialog.Builder(this).setTitle("Atencion").setMessage(m).setPositiveButton("Ok"){d,i->}.show()
    }

    fun ponerTexto(t1:String,t2:String,t3:String){
        editText.setText(t1)
        editText2.setText(t2)
        editText3.setText(t3)
    }
}


