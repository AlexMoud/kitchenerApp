package gr.hua.it21533.kitchenerMap.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gr.hua.it21533.kitchenerMap.R
import gr.hua.it21533.kitchenerMap.adapters.TypesAdapter
import gr.hua.it21533.kitchenerMap.interfaces.MenuView
import gr.hua.it21533.kitchenerMap.models.TypesModel
import kotlinx.android.synthetic.main.fragment_types_of_places.*

class TypesOfPlacesFragment: Fragment() {

    private var types = ArrayList<TypesModel>()
    private var selectedTypes = ArrayList<String>()
    private var started = false
    var delegate: MenuView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_types_of_places, container, false)
    }

    override fun onStart() {
        super.onStart()
        if(!started) {
            addCheckboxesContent()
        }
        started = true
        addTypesOfPlacesCheckboxes()
        backToMenu.setOnClickListener {
            delegate?.backToMenu()
        }
    }

    private fun addCheckboxesContent() {
        types.add(TypesModel("cafe", resources.getString(R.string.types_of_places_coffee_shops)))
        types.add(TypesModel("bank", resources.getString(R.string.types_of_places_banks)))
        types.add(TypesModel("lodging", resources.getString(R.string.types_of_places_lodging)))
        types.add(TypesModel("museum", resources.getString(R.string.types_of_places_museum)))
        types.add(TypesModel("hospital", resources.getString(R.string.types_of_places_hospitals)))
        types.add(TypesModel("political", resources.getString(R.string.types_of_places_political)))
        types.add(TypesModel("library", resources.getString(R.string.types_of_places_library)))
        types.add(TypesModel("restaurant", resources.getString(R.string.types_of_places_restaurant)))
        types.add(TypesModel("supermarket", resources.getString(R.string.types_of_places_supermarket)))
    }

    private fun addTypesOfPlacesCheckboxes() {
        typesCheckboxes.layoutManager = LinearLayoutManager(context)
        typesCheckboxes.adapter = TypesAdapter(types, context!!, selectedTypes) { item: String -> itemTypeClicked(item) }
    }

    private fun itemTypeClicked(item: String) {
        if (selectedTypes.contains(item)) {
            selectedTypes.remove(item)
        } else {
            selectedTypes.add(item)
        }
        delegate?.didFilterChange(selectedTypes.joinToString(), "types")
    }
}