package com.sadestorm.iesblab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditarPerfilUsuario extends AppCompatActivity {
    TextView nomeTxt;
    TextView matriculaTxt;

    EditText emailTxt;
    EditText telefoneTxt;


    Button salvarBtn;
    Button cancelarBtn;

    Usuario usuario = new Usuario();

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autentica = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_usuario);

        nomeTxt = findViewById(R.id.txtNome);
        matriculaTxt = findViewById(R.id.txtMatricula);
        emailTxt = findViewById(R.id.txtEmail);
        telefoneTxt = findViewById(R.id.txtTelefone);


        salvarBtn = findViewById(R.id.btnSalvar);
        cancelarBtn = findViewById(R.id.btnCancelar);

      //  carregaDados();

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                usuario.setEmail(emailTxt.getText().toString());
                usuario.setTelefone(telefoneTxt.getText().toString());
                atualizaDados();

                Intent iPerfil = new Intent(EditarPerfilUsuario.this, PerfilUsuario.class);
                startActivity(iPerfil);
            }
        });

        cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iPerfil = new Intent(EditarPerfilUsuario.this, PerfilUsuario.class);
                startActivity(iPerfil);
            }
        });
    }

    public void carregaDados() {


        final String email;
        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");
        email = autentica.getCurrentUser().getEmail(); // verifica email logado.

        Query consulta = dbUsuario.orderByChild("email").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()) {

                    if (dt.child("email") != null && dt.child("email").getValue().equals(email)) {

                        nomeTxt.setText(dt.child("nome").getValue().toString());
                        matriculaTxt.setText(dt.child("matricula").getValue().toString());
                        emailTxt.setText(dt.child("email").getValue().toString());
                        telefoneTxt.setText(dt.child("telefone").getValue().toString());

                        usuario.setNome(nomeTxt.getText().toString());
                        usuario.setFuncao(dt.child("funcao").getValue().toString());
                        usuario.setEmail(emailTxt.getText().toString());
                        usuario.setTelefone(telefoneTxt.getText().toString());
                        usuario.setMatricula(matriculaTxt.getText().toString());
                        usuario.setConfirma("1");

                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizaDados() {

        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");

        Query consulta = dbUsuario.orderByChild("matricula");
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usuario.setSenha(null);

                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    if (dt.getKey() != null && dt.getKey().equals(usuario.getMatricula())) {

                        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");
                        dbUsuario.orderByChild("Usuario").equalTo(usuario.getMatricula());

                        FirebaseUser atualizaEmail = FirebaseAuth.getInstance().getCurrentUser();

                        atualizaEmail.updateEmail(usuario.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(EditarPerfilUsuario.this, "email atualizado!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        dbUsuario.child(usuario.matricula).setValue(usuario);
                        Toast.makeText(EditarPerfilUsuario.this, "Dados Atualizados com sucesso!", Toast.LENGTH_SHORT).show();
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





