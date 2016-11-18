package ir.parsdroidgroup.searchlistexample.model;


import ir.parsdroidgroup.searchlistexample.adapter.SearchableModel;

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public class Contact implements SearchableModel<String> {

    public String phoneNumber;
    public String name;

    public Contact(String phone, String name) {
        this.phoneNumber = phone;
        this.name = name;

    }

    @Override
    public String getSearchArgument() {
        return name;
    }
}

