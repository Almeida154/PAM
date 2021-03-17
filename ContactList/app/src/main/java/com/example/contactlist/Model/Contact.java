package com.example.contactlist.Model;

public class Contact {

    // Properties

    private long idContact;
    private String nameContact, numberContact, emailContact, landlineContact, nicknameContact;

    // Constructor

    public Contact(String nameContact, String numberContact, String emailContact, String landlineContact,
                   String nicknameContact) {
        this.setNameContact(nameContact);
        this.setNumberContact(numberContact);
        this.setEmailContact(emailContact);
        this.setLandlineContact(landlineContact);
        this.setNicknameContact(nicknameContact);
    }

    // Getters and Setters

    public long getIdContact() {
        return idContact;
    }

    public void setIdContact(long idContact) {
        this.idContact = idContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    public void setNumberContact(String numberContact) {
        this.numberContact = numberContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public String getLandlineContact() {
        return landlineContact;
    }

    public void setLandlineContact(String landlineContact) {
        this.landlineContact = landlineContact;
    }

    public String getNicknameContact() {
        return nicknameContact;
    }

    public void setNicknameContact(String nicknameContact) {
        this.nicknameContact = nicknameContact;
    }
}