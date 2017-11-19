package ir.parsdroidgroup.searchlistexample.adapter

/**
 * Zigzag Project
 * Created by Seyyed Davud Hosseiny on 7/4/2017.
 */

interface Searchable<out T> {

    val searchArgument: T?

    val itemID: Any
}