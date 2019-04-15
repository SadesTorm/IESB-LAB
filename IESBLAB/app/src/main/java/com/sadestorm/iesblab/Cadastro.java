package com.sadestorm.iesblab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Cadastro extends AppCompatActivity {
    //DECLARAÇÃO DE CAIXAS DE TEXTO

    EditText matriculaTxt;
    EditText nomeTxt;
    EditText telefoneTxt;
    EditText emailTxt;
    EditText senhaTxt;

    //DECLARAÇAO DE BOTÕES

    Button cadastrarBtn;


    private RadioGroup funcaoRg;

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // VARIAVEIS TIPO EDIT TEXT

        matriculaTxt = findViewById(R.id.txtMatricula);
        nomeTxt = findViewById(R.id.txtNome);
        telefoneTxt = findViewById(R.id.txtTelefone);
        emailTxt = findViewById(R.id.txtEmail);
        senhaTxt = findViewById(R.id.txtSenha);


        // VARIAVEIS TIPO BOTÃO

        cadastrarBtn = findViewById(R.id.btnCadastrar);
        funcaoRg = findViewById(R.id.rgFuncao);


        verificaFuncao();

        cadastrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cadastrar(matriculaTxt.getText().toString(), nomeTxt.getText().toString(), telefoneTxt.getText().toString(), emailTxt.getText().toString(), senhaTxt.getText().toString(), usuario.getFuncao());
                Intent iLogin = new Intent(Cadastro.this, MainActivity.class);
                startActivity(iLogin);
            }
        });

    }

    public void verificaFuncao() {

        funcaoRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

                if (i == R.id.rbCoordenador) {
                    usuario.setFuncao("Coordenador");
                } else if (i == R.id.rbProfessor) {
                    usuario.setFuncao("Professor");
                }
            }
        });
    }

    public void cadastrar(final String matricula, String nome, String telefone, String email, String senha, String funcao) {


        usuario.setMatricula(matricula);
        usuario.setNome(nome);
        usuario.setTelefone(telefone);
        usuario.setEmail(email);
        usuario.setFuncao(funcao);
        usuario.setSenha(senha);
        usuario.setConfirma("0");


        final FirebaseAuth cadastroLogin = FirebaseAuth.getInstance();

        DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");

        Query consulta = dbUsuario.orderByChild("matricula");
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String senha = usuario.getSenha();
                usuario.setSenha(null);

                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    if (dt.getKey() != null && dt.getKey().equals(matricula)) {
                        Toast.makeText(Cadastro.this, "Matricula ou E-mail existente!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (dt.child("email") != null && dt.child("email").getValue().equals(usuario.getEmail())) {
                        Toast.makeText(Cadastro.this, "Matricula ou E-mail existente!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                DatabaseReference dbUsuario = referencia.child("Iesb").child("Usuario");

                dbUsuario.child(usuario.matricula).setValue(usuario);

                //CADASTTRANDRO LOGIN E SENHA NO AUTENTICADOR

                cadastroLogin.createUserWithEmailAndPassword(usuario.getEmail(), senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Cadastro.this, "Email cadastrador com sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Cadastro.this, "Erro ao Cadastrar o email ou Email existente!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Toast.makeText(Cadastro.this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

