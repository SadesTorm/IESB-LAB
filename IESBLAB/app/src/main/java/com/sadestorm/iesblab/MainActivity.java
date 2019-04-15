package com.sadestorm.iesblab;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


        // progress dialog /*/*/*/*/*/*/*/***/*/*/*/*/*/*/*/*/*


    EditText matriculaTxt;
    EditText senhaTxt;

    Button loginBtn;
    Button cadastrarBtn;

    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        matriculaTxt = findViewById(R.id.txtMatricula);
        senhaTxt = findViewById(R.id.txtSenha);

        cadastrarBtn = findViewById(R.id.btnCadastrar);
        loginBtn = findViewById(R.id.btnLogin);

        cadastrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCadastro = new Intent(MainActivity.this, Cadastro.class);
                startActivity(iCadastro);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              login(matriculaTxt.getText().toString(), senhaTxt.getText().toString(),MainActivity.this);

            }
        });
    }

    public void login(final String matriculaTxt, final String senha, final Activity activity) {





        final FirebaseAuth autenticaLogin = FirebaseAuth.getInstance();
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");

        Query consulta = dbUsuario.orderByChild("matricula");

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dt : dataSnapshot.getChildren()){
                    if (dt.getKey() != null && dt.getKey().equals(matriculaTxt) && dt.child("confirma").getValue().toString().equals("1") ) {


                        usuario.setEmail(dt.child("email").getValue().toString());

                        Task task = autenticaLogin.signInWithEmailAndPassword(usuario.getEmail(),senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = autenticaLogin.getCurrentUser();
                                    Toast.makeText(activity, "logando:" + user.getEmail(), Toast.LENGTH_SHORT).show();
                                    Intent iLogin = new Intent(activity, PerfilUsuario.class);
                                    startActivity(iLogin);

                                } else {
                                    Toast.makeText(activity, "Email ou senha invalido.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(activity, "Matricula aguardando confirmação ou invalida", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}