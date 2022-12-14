package com.example.cavaleiro_da_lua;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class reviewClass implements Serializable {

    private int _id;
    private String _nome;
    private String _email;
    private String _mensagem;

    public reviewClass(){}

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_mensagem(String _mensagem) {
        this._mensagem = _mensagem;
    }


    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Id", this._id);
            obj.put("Nome", this._nome);
            obj.put("Email", this._email);
            obj.put("Mensagem", this._mensagem);
        } catch (JSONException e) {
            //trace("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
}