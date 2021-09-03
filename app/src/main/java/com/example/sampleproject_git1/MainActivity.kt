package com.example.sampleproject_git1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a1recyclerviewadapter.A1RecyclerAdapter
import com.example.sampleproject_git1.defaultdata.ExperienceDetails
import com.example.sampleproject_git1.defaultdata.UserProfile

class MainActivity : AppCompatActivity()
{
    lateinit var jobExperienceRecyclerView: RecyclerView
    val yearList: MutableList<String> = mutableListOf("2019", "2018")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jobExperienceRecyclerView = findViewById(R.id.recyclerView)
        val userDetails = getUserDetails()
        val userExperienceList = getUserExperience()

        val adapter = object : A1RecyclerAdapter<ExperienceDetails>(this, userExperienceList)
        {
            override val layoutResourceId: Int
                get() = R.layout.item_elements

            //optional
            override val headerResourceId: Int
                get() = R.layout.layout_userdetails_header
            override val firstItemLayout: Int
                get() = R.layout.item_firstelement
            override val customSeparatorLayoutId: Int
                get() = R.layout.layout_separator
            override val footerResourceId: Int
                get() = R.layout.layout_customfooter

            override fun getSeparatorIndex(): MutableList<Int>
            {
                return mutableListOf(4, 8)
            }

            override fun onDataBind(model: ExperienceDetails, position: Int, holder: RecyclerView.ViewHolder?)
            {
                val infoTextView = holder?.itemView?.findViewById<TextView>(R.id.infodetails)
                val dateInfoTextView = holder?.itemView?.findViewById<TextView>(R.id.date)
                infoTextView?.text = model.details
                dateInfoTextView?.text = model.date
            }

            override fun onFirstItemBind(model: ExperienceDetails, holder: RecyclerView.ViewHolder?)
            {
                val infoTextView = holder?.itemView?.findViewById<TextView>(R.id.details)
                val dateInfoTextView = holder?.itemView?.findViewById<TextView>(R.id.date)
                infoTextView?.text = model.details
                dateInfoTextView?.text = model.date
            }

            override fun onSeparatorBind(index: Int, holder: View?)
            {
                val infoTextView = holder?.findViewById<TextView>(R.id.seperatortext)
                infoTextView?.text = yearList[index]

            }

            override fun onFooterViewBind(holder: RecyclerView.ViewHolder?)
            {
                holder?.itemView?.findViewById<Button>(R.id.button)?.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Education page", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onHeaderViewBind(holder: RecyclerView.ViewHolder?)
            {
                val nameTextView = holder?.itemView?.findViewById<EditText>(R.id.name)
                val addressTextView = holder?.itemView?.findViewById<EditText>(R.id.addres)
                val telephoneTextView = holder?.itemView?.findViewById<EditText>(R.id.telephone)
                val experienceTextView = holder?.itemView?.findViewById<TextView>(R.id.experience)
                val editSwitch = holder?.itemView?.findViewById<SwitchCompat>(R.id.editswitch)

                nameTextView?.setText(userDetails.userName)
                addressTextView?.setText(userDetails.userAddress)
                telephoneTextView?.setText(userDetails.contact)
                experienceTextView?.text = "Experience :" + userDetails.totalExperience
                editSwitch?.setOnCheckedChangeListener { compoundButton, b ->
                    if (b)
                    {
                        Toast.makeText(this@MainActivity, "Edit mode", Toast.LENGTH_SHORT).show()
                        nameTextView?.isEnabled = true
                        addressTextView?.isEnabled = true
                        telephoneTextView?.isEnabled = true

                    }
                    else
                    {
                        nameTextView?.isEnabled = false
                        addressTextView?.isEnabled = false
                        telephoneTextView?.isEnabled = false

                    }
                }
            }
        }
        jobExperienceRecyclerView.adapter = adapter


    }

    fun getUserDetails(): UserProfile
    {
        return UserProfile("test user 123", "test user address line1 ", "12346589", 8)
    }

    fun getUserExperience(): List<ExperienceDetails>
    {
        return listOf(
                ExperienceDetails("Executive Chef of plaza 1", "June 2020 - Current"),

                ExperienceDetails("Head Chef of plaza 1", "Mar 2020 - June 2020"),
                ExperienceDetails("Head Chef of plaza 2", "Jan 2020 - Mar 2020"),

                ExperienceDetails("Deputy Chef (Sous Chef) of plaza 3", "Sept 2019 - Dec 2019"),
                ExperienceDetails("Deputy Chef (Sous Chef) of plaza 4", "July 2019 - Sept 2019"),
                ExperienceDetails("Deputy Chef (Sous Chef) of plaza 5", "May 2019 - July 2019"),
                ExperienceDetails("Deputy Chef (Sous Chef) of plaza 6", "Jan 2019 - July 2019"),

                ExperienceDetails("Junior Chef (Commis Chef) of plaza 7", "Sept 2018 - Dec 2018"),
                ExperienceDetails("Junior Chef (Commis Chef) of plaza 8", "July 2018 - Sept 2018"),
                ExperienceDetails("Junior Chef (Commis Chef) of plaza 9", "May 2018 - July 2018"),
                ExperienceDetails("Junior Chef (Commis Chef) of plaza 10", "Jan 2018 - July 2018"),
                     )
    }
}