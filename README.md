# A1RecyclerVIewAdapter
A RecyclerView adapter that displays user defined  header , footer and separator views 

basic scenario [ mandatory methods and fields ]
An adapter that accepts custom data class and displays it in a recyclerview with the layout  "layoutResourceId" defined by the user and is customizable

    val adapter = object : A1RecyclerAdapter<ExperienceDetails>(this, userExperienceList)
        {
            override val layoutResourceId: Int
                get() = R.layout.item_elements
            override fun onDataBind(model: ExperienceDetails, position: Int, holder: RecyclerView.ViewHolder?)
            {
                val infoTextView = holder?.itemView?.findViewById<TextView>(R.id.infodetails)
                val dateInfoTextView = holder?.itemView?.findViewById<TextView>(R.id.date)
                infoTextView?.text = model.details
                dateInfoTextView?.text = model.date
            }
       }

recyclerView.adapter = adapter

-------------------------------------------------------------------------------------------------

the adapter also contains optional features(layouts that can be defined)
---> HEADER AND FOOTER


    val adapter = object : A1RecyclerAdapter<ExperienceDetails>(this, userExperienceList)
        {
            ....
            override val headerResourceId: Int
                get() = R.layout.layout_userdetails_header
            override val footerResourceId: Int
                get() = R.layout.layout_customfooter
            override fun onFooterViewBind(holder: RecyclerView.ViewHolder?)
            {
                holder?.itemView?.findViewById<Button>(R.id.button)?.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Education page", Toast.LENGTH_SHORT).show() }
  
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

----> CUSTOM LAYOUT FOR FIRST ELEMENT

     val adapter = object : A1RecyclerAdapter<ExperienceDetails>(this, userExperienceList)
        {
          ..........

          override val firstItemLayout: Int
                get() = R.layout.item_firstelement
          override fun onFirstItemBind(model: ExperienceDetails, holder: RecyclerView.ViewHolder?)
            {
                val infoTextView = holder?.itemView?.findViewById<TextView>(R.id.details)
                val dateInfoTextView = holder?.itemView?.findViewById<TextView>(R.id.date)
                infoTextView?.text = model.details
                dateInfoTextView?.text = model.date
            }
        }


----> SEPERATION VIEW ON SPECIFIC POSITION

     val adapter = object : A1RecyclerAdapter<ExperienceDetails>(this, userExperienceList)
        {
          ..........

                override val customSeparatorLayoutId: Int
                get() = R.layout.layout_separator

             override fun getSeparatorIndex(): MutableList<Int>
            {
                return mutableListOf(4, 8)
            }
              override fun onSeparatorBind(index: Int, holder: View?)
            {
                val infoTextView = holder?.findViewById<TextView>(R.id.seperatortext)
                infoTextView?.text = yearList[index]
            }
       }

