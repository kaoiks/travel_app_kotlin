package com.example.myapplication.pages.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.client.ApiClient
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings


@Composable
fun TicketList(tickets: List<ApiClient.Ticket>) {

    LazyColumn {
        items(tickets) { ticket ->
            TicketItem(ticket)
        }
    }
}

@Composable
fun TicketItem(ticket: ApiClient.Ticket) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Ticket ID: ${ticket.id}")
            Text(text = "Name: ${ticket.name}")
            Text(text = "Travel Date: ${ticket.travel_date}")
            Text(text = "Start Location: ${ticket.start_location ?: "N/A"}")
            Text(text = "End Location: ${ticket.end_location ?: "N/A"}")
        }
    }
}