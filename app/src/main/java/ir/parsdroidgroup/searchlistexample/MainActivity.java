package ir.parsdroidgroup.searchlistexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.izigzag.messenger.ui.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import ir.parsdroidgroup.searchlistexample.adapter.ContactsAdapter;
import ir.parsdroidgroup.searchlistexample.model.Contact;

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;

    private final String[] names = new String[]{
            "Sophia",
            "Emma",
            "Olivia",
            "Isabella",
            "Ava",
            "Chloe",
            "Abigail",
            "Addison",
            "Lily",
            "Zoe",
            "Mia",
            "Madison",
            "Emily",
            "Ella",
            "Madelyn",
            "Aubrey",
            "Layla"};
    private final String[] phoneNumbers = new String[]{
            "9123456787",
            "9123456789",
            "9123456788",
            "9123456786",
            "9123456785",
            "9123456782",
            "9123456784",
            "9123456783",
            "9123456781",
            "9123456780",
            "9123456778",
            "9123456774",
            "9123456775",
            "9123456779",
            "9123456777",
            "9123456773",
            "9123456776"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SearchToolbar searchToolbar = findViewById(R.id.toolbar_search);
        searchToolbar.setTitle(R.string.app_name);
        contacts = new ArrayList<>(names.length);
        populateData();

        ContactsAdapter adapter = new ContactsAdapter(contacts, (OnItemClickListener<Contact>) item -> Toast.makeText(MainActivity.this, "Name: " + item.getName() + " Phone number: " + item.getMsisdn(), Toast.LENGTH_LONG).show());

        searchToolbar.setSearchListener(adapter);

        RecyclerView recyclerView = findViewById(R.id.recycler_contacts);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateData() {

        for (int i = 0; i < names.length; i++) {

            Contact contact = new Contact(phoneNumbers[i], names[i]);
            contacts.add(contact);
        }
    }
}
