package com.example.cavaleiro_da_lua;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;


public class QuizMaker implements Serializable {
    private String _nome;
    private String _email;
    private String _senha;

    public QuizMaker(String _nome, String _email, String _senha) {
        this._nome = _nome;
        this._email = _email;
        this._senha = _senha;
    }

    /*
    public String get_nome() { return _nome; }
    public void set_nome(String _nome) { this._nome = _nome; }

    public String get_email() { return _email; }
    public void set_nome(String _email) { this._email = _email; }
    */

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Nome", this._nome);
            obj.put("Email", this._email);
            obj.put("Senha", this._senha);
        } catch (JSONException e) {
            //trace("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
}
