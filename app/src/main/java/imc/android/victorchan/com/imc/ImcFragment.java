package imc.android.victorchan.com.imc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alumno on 08/02/19.
 */

public class ImcFragment extends Fragment {
    private final String KEY_IMC="imc";
    private final String KEY_ESTADO = "estado";
    private EditText mCampoPeso;
    private EditText mCampoEstatura;
    private Button mBotonCalcular;
    private Button mBotonLimpiar;
    private TextView mImcTextView;
    private double imc;
    private TextView mEstadoTextView;
    private String estadoTextView;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imc,container, false);

        mCampoPeso = view.findViewById(R.id.campo_peso);
        mCampoEstatura = view.findViewById(R.id.campo_estatura);
        mBotonCalcular= view.findViewById(R.id.boton_calcular);
        mEstadoTextView = view.findViewById(R.id.estado_nutricional);
        mBotonLimpiar= view.findViewById(R.id.boton_limpiar);
        mImcTextView = view.findViewById(R.id.mImcTextView);

        mBotonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mCampoPeso.getText().toString();
                double peso = Double.parseDouble(s);
                s=mCampoEstatura.getText().toString();
                double estatura = Double.parseDouble(s);
                imc = peso / Math.pow(estatura, 2);
                mImcTextView.setText(Double.toString(imc));

                if(imc<18.5){
                    mEstadoTextView.setText("Peso bajo");
                }

                if(imc>18.5 && imc<24.99){
                    mEstadoTextView.setText("Peso normal");
                }

                if(imc>25.0 && imc<29.99){
                    mEstadoTextView.setText("Sobrepeso");
                }

                if(imc>30.0 && imc<39.99){
                    mEstadoTextView.setText("Obesidad");
                }

                if(imc>40.0){
                    mEstadoTextView.setText("Obesidad extrema");
                }

            }
        });

        mBotonLimpiar = view.findViewById(R.id.boton_limpiar);
        mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCampoPeso.setText("");
                mCampoEstatura.setText("");
                mImcTextView.setText("");
                mEstadoTextView.setText("");
            }
        });

        if(savedInstanceState!=null)
        {
            imc = savedInstanceState.getDouble(KEY_IMC);//Recuperacion de la llave con un double
            mImcTextView.setText(Double.toString(imc));
            estadoTextView = savedInstanceState.getString(KEY_ESTADO);
            mEstadoTextView.setText(estadoTextView);
        }
        else{
            imc=0.0;
            mImcTextView.setText("");
            estadoTextView = "";
            mEstadoTextView.setText("");
        }


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_IMC, imc);//Se encarga de guardar en el diccionario el dato
        outState.putString(KEY_ESTADO, mEstadoTextView.getText().toString());
    }
}
