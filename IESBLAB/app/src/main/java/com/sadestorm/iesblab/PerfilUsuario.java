package com.sadestorm.iesblab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PerfilUsuario extends AppCompatActivity {

    TextView nomeTxt;
    TextView funcaoTxt;

    Button novaResevaBtn;
    Button resevasBtn;
    Button reclamacoesBtn;
    Button editarPerfilBtn;
    Button feedbackBtn;
    Button sairBtn;


    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth verificaEmail = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        nomeTxt = findViewById(R.id.txtNomePerfil);
        funcaoTxt = findViewById(R.id.txtFuncao);

        novaResevaBtn = findViewById(R.id.btnNovaReserva);
        resevasBtn = findViewById(R.id.btnResevas);
        reclamacoesBtn = findViewById(R.id.btnReclamacoes);
        editarPerfilBtn = findViewById(R.id.btnEditarPerfil);
        feedbackBtn = findViewById(R.id.btnFeedback);
        sairBtn = findViewById(R.id.btnSair);

        carregaDados();

        novaResevaBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iNovaReseva = new Intent(PerfilUsuario.this, PerfilGestor.class);
                startActivity(iNovaReseva);
            }
        });

        editarPerfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEditarPerfil = new Intent(PerfilUsuario.this, EditarPerfilUsuario.class);
                startActivity(iEditarPerfil);
            }
        });

        sairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSair = new Intent(PerfilUsuario.this, MainActivity.class);
                verificaEmail.signOut();
                startActivity(iSair);
            }
        });
    }

    public void carregaDados() {

        final String email;
        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");
        email = verificaEmail.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)) {

                        nomeTxt.setText(dt.child("nome").getValue().toString());
                        funcaoTxt.setText(dt.child("funcao").getValue().toString());

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
