package gr.hua.it21533.kitchenerMap.activities

import gr.hua.it21533.kitchenerMap.networking.ApiModel

interface MapsActivityView {
    fun displayMarkers(markers: Array<ApiModel.Results>?)
}