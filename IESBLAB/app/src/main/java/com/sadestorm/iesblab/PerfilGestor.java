package com.sadestorm.iesblab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PerfilGestor extends AppCompatActivity {

    TextView nomeTxtView;
    TextView funcaoTxtView;

    Button novaReservaBtn;
    Button reservasBtn;
    Button cadastarLaboratorioBtn;
    Button gerenciarUsuarioBtn;
    Button relatorioBtn;
    Button reclamacaoBtn;
    Button editarPerfilBtn;



    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth verificaEmail = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_gestor);

        nomeTxtView = findViewById(R.id.txtNome);
        funcaoTxtView = findViewById(R.id.txtFuncao);

        novaReservaBtn = findViewById(R.id.btnNovaReserva);
        reservasBtn = findViewById(R.id.btnReservas);
        cadastarLaboratorioBtn = findViewById(R.id.btnCadastrarLaboratorio);
        gerenciarUsuarioBtn = findViewById(R.id.btnGerenciarUsuario);
        relatorioBtn = findViewById(R.id.btnRelatorio);
        reclamacaoBtn = findViewById(R.id.btnReclamacao);
        editarPerfilBtn = findViewById(R.id.btnEditarPerfil);
        carregaDados();

        gerenciarUsuarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGerenciaUsuario = new Intent(PerfilGestor.this, GerenciarUsuario.class);
                startActivity(iGerenciaUsuario);
            }
        });




    }

    public void carregaDados(){

        final String email;
        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");
        email = verificaEmail.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)){

                        nomeTxtView.setText(dt.child("nome").getValue().toString());
                        funcaoTxtView.setText(dt.child("funcao").getValue().toString());

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



