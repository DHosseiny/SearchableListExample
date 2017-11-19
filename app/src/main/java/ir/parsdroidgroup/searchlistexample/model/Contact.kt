package ir.parsdroidgroup.searchlistexample.model


import ir.parsdroidgroup.searchlistexample.adapter.Searchable

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

class Contact(val msisdn: String//phone number
              , val name: String) : Searchable<String> {
    override val searchArgument: String?
        get() = name

    override val itemID: Any
        get() = msisdn
}

