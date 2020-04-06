package net.lrivas.miagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.lrivas.miagenda.clases.Configuraciones;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarContacto extends AppCompatActivity {
    EditText nombre, telefono;
    Button botonAgregar, botonRegresar;
    Configuraciones objConfiguracion = new Configuraciones();
    String URL = objConfiguracion.urlWebServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contacto);


        nombre = findViewById(R.id.txtNombreCompleto);
        telefono = findViewById(R.id.txtTelefono);
        botonAgregar = findViewById(R.id.btnGuardarContacto);
        botonRegresar = findViewById(R.id.btnRegresar);

        //Evento (CLICK)
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Registrar
                registrar();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Regresar
                regresar();
            }
        });
    }

    private void regresar(){
        Intent actividad = new Intent(RegistrarContacto.this,MainActivity.class);
        startActivity(actividad);
        RegistrarContacto.this.finish();
    }

    private void registrar(){
        try{
            RequestQueue objetoPeticion = Volley.newRequestQueue(RegistrarContacto.this);
            StringRequest peticion = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject objJSONResultado = new JSONObject(response.toString());
                        String estado = objJSONResultado.getString("estado");
                        if(estado.equals("1")){
                            Toast.makeText(RegistrarContacto.this, "Contacto Registrado con exito", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistrarContacto.this, "Error: "+ estado, Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrarContacto.this, "Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("accion","registrar");
                    params.put("nombre",nombre.getText().toString());
                    params.put("telefono",telefono.getText().toString());
                    return params;
                }
            };

            objetoPeticion.add(peticion);
        }catch (Exception error){
            Toast.makeText(RegistrarContacto.this, "Error en tiempo de ejecucion: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
