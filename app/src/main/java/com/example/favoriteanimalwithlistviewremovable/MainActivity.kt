package com.example.favoriteanimalwithlistviewremovable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Note that it is not really necessary to make these variables global in this example
    // but it may help you in a different context where you actually want to make them global variables

    // Create a list of some strings that will be shown in the listview
    private val animalList = ArrayList<String>()

    //lateinit allows to initialize variable before the code runs so no need to initialize the variable to null here
    lateinit var myAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Put some items to list
        animalList.add("dog")
        animalList.add("cat")
        animalList.add("bear")
        animalList.add("rabbit")


        // Create an adapter with 3 parameters: activity (this), layout, list
        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, animalList)

        // set the adapter to listview
        animal_list.adapter = myAdapter


        // Registering setOnItemClickListener that listens item click events in the listview
        animal_list.setOnItemClickListener { list, item, position, id ->

            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()


            // Based on the index of position selected, set the corresponding image
            val imageId = when(position){
                0 -> R.drawable.dog
                1 -> R.drawable.cat
                2 -> R.drawable.bear
                else -> R.drawable.rabbit
            }

            image_animal.setImageResource(imageId)

        }


        // Registering setOnItemLongClickListener callback method to be invoked when an item in this list has been clicked and held.
        // This callback methods returns boolean, true if the callback consumed the long click, false otherwise
        animal_list.setOnItemLongClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "This is a long press, Deleting $selectedItem", Toast.LENGTH_SHORT).show()

            animalList.removeAt(position)

            myAdapter.notifyDataSetChanged()

            return@setOnItemLongClickListener true

        }
    }
}
