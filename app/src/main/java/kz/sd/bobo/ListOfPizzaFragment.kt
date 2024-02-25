package kz.sd.bobo

import android.text.Editable
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kz.sd.bobo.databinding.FragmentListOfPizzaBinding
import kz.sd.bobo.model.Pizza

class ListOfPizzaFragment:BaseFragment<FragmentListOfPizzaBinding>(FragmentListOfPizzaBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = PizzaAdapter()
        binding.listPizza.adapter = adapter
        binding.listPizza.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(getList())
        adapter.itemClick = {it ->
            findNavController().navigate(ListOfPizzaFragmentDirections.actionListOfPizzaFragmentToPizzaDetailsFragment(it.title, it.img, it.desc,it.price, it.size))
        }
        binding.editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                if(binding.editText.text!!.isNotEmpty()){
                    adapter.submitList(searchPizza(binding.editText.text.toString()))
                }
                else{
                    Toast.makeText(requireContext(), "Enter the pizza name", Toast.LENGTH_SHORT).show()
                }
            }

            false
        })
    }

    private fun getList():List<Pizza>{
        return listOf(
            Pizza("Chorizo fresh", R.drawable.img, "Spicy chorizo, sweet pepper, mozzarella cheese, marinara sauce", 2900, "Medium" ),
            Pizza("Chicken ", R.drawable.img_1, "Double chicken, mozzarella cheese, Alfredo sauce", 2500, "Medium" ),
            Pizza("Bavarian ", R.drawable.img_2, "Spicy chorizo, pickles, red onion, tomatoes, mustard sauce, mozzarella cheese, marinara sauce", 3500, "Large" ),
            Pizza("Neapolitan", R.drawable.img_3, "Tomatoes, garlic, oregano, and extra virgin olive oil", 3500, "Small" ),
            Pizza("Chicago", R.drawable.img_4, "Mozzarella, ground beef, sausage, pepperoni, onion, mushrooms, and green peppers, placed underneath the tomato sauce", 3400, "Small" ),
            Pizza("Sicilian", R.drawable.img_5, "Pillowy dough, a crunchy crust, and robust tomato sauce", 4000, "Small" ),
            Pizza("California", R.drawable.img_6, "Mustard, ricotta, pate, and red pepper, and by chance", 5500, "Small" ),
            Pizza("Detroit", R.drawable.img_7, "Pepperoni, brick cheese (usually Wisconsin brick cheese), and tomato sauce", 4500, "Small" ),
            Pizza("St. Louis Style", R.drawable.img_8, "Provel cheese and a sweeter tomato sauce with a hefty dosage of oregano", 3500, "Small" ),
            )
    }
    private fun searchPizza(input: String):List<Pizza>{
        val pizzas = getList()
        return pizzas.filter{pizza->
            pizza.title.contains(input, ignoreCase = true)
        }
    }
}