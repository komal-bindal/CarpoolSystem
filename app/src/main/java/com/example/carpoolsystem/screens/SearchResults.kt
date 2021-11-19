package com.example.carpoolsystem.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carpoolsystem.R
import com.example.carpoolsystem.databinding.ActivitySearchResultsBinding
import com.example.carpoolsystem.screens.adapter.ParentAdapter
import com.example.carpoolsystem.screens.model.Parent

class SearchResults : AppCompatActivity() {
    val list = mutableListOf<Parent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        val binding = DataBindingUtil.setContentView<ActivitySearchResultsBinding>(
            this,
            R.layout.activity_search_results
        )

        initList()


        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(this@SearchResults)
            adapter = ParentAdapter(list)
        }


    }


    private fun initList() {
        list.add(
            Parent(
                "Driver 1",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 2",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 3",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 4",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 5",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 6",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 7",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 8",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 9",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 10",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
        list.add(
            Parent(
                "Driver 11",
                mutableListOf(
                    "Source:Masani",
                    "Destination:Gla",
                    "Time:9:30 am",
                    "Vehicle Number: UP 80 AB 9917",
                    "Date:21/10/21",
                    "Accept/Reject"
                )
            )
        )
    }
}