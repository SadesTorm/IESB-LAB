package com.sadestorm.iesblab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReclamacoeUsuario extends AppCompatActivity {

    Button enviarBtn;
    EditText txtReclame;
    private DatabaseReference referenciaUsuario = FirebaseDatabase.getInstance().getReference("Iesb/Usuario");
    private String mat;
    FirebaseAuth verificaEmail = FirebaseAuth.getInstance();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Iesb");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacoe_usuario);
        verificaUsuario();
        txtReclame = findViewById(R.id.etxtReclame);
        enviarBtn = findViewById(R.id.btnEnviar);

        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reclame aux = new Reclame(txtReclame.getText().toString(),mat);
                DatabaseReference dbUsuario = referencia.child("Reclamacoes");
                dbUsuario.push().setValue(aux);
            }
        });

    }
    public void verificaUsuario() {

        final String email;
        DatabaseReference dbUsuario = referenciaUsuario;
        email = verificaEmail.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)) {


                        mat = dt.child("matricula").getValue().toString();
                        String fun = dt.child("funcao").getValue().toString();

                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
