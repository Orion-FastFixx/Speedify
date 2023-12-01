package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.speedify.R
import com.example.speedify.databinding.ActivityCheckoutBengkelBinding
import com.example.speedify.feature_bengkel.domain.entity.LayananEntity
import com.example.speedify.utils.currencyFormat
import com.example.speedify.utils.currencyFormatWithoutRp
import java.io.Serializable

class CheckoutBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCheckoutBengkelBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicConfiguration()
    }

    private fun dynamicConfiguration() {
//      Set text order summary
        val orderSummary = binding.checkoutBengkel.orderSummary.tvSectionName
        orderSummary.text = getString(R.string.order_summary)
//      End set text order summary

//        Set text payment method
        val paymentMethod = binding.checkoutBengkel.tvPaymentMethod
        paymentMethod.text = getString(R.string.payment_method)
//        End set text payment method

//        Get user data
        getUserData()
//        End get user data

//      Go to Payment Methods
        val morePayment = binding.checkoutBengkel.icArrowRightPaymentMethod
        morePayment.setOnClickListener {
            val intent = Intent(this, PaymentMethodsActivity::class.java)
            startActivity(intent)
        }
//        End go to Payment Methods

//        Button pay
        setupTextChangeListeners()
        updateButtonState()
        payService()
//        End button pay

    }

    private fun getUserData() {
        val detailLocation = binding.checkoutBengkel.titleDetailLocationEditText
        val userName = binding.checkoutBengkel.titleFullNameEditText
        val detailComplaints = binding.checkoutBengkel.titleDetailComplaintEditText

//        get field data from DetailBengkelActivity
        val location = intent.getStringExtra(DETAIL_LOCATION)
        val name = intent.getStringExtra(USER_NAME)
        val complaints = intent.getStringExtra(DETAIL_COMPLAINT)

        val selectedServices: ArrayList<LayananEntity> =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // For newer SDKs (Adjust the SDK version as per your requirement)
                val rawServices = intent.getSerializableExtra(SELECTED_SERVICES)
                safelyCastToLayananEntityList(rawServices)
            } else {
                // For older SDKs
                @Suppress("DEPRECATION") val rawServices =
                    intent.getSerializableExtra(SELECTED_SERVICES)
                safelyCastToLayananEntityList(rawServices)
            }


//        set field data to CheckoutBengkelActivity
        detailLocation.setText(location)
        userName.setText(name)
        detailComplaints.setText(complaints)
        displaySelectedServicesAndPrice(selectedServices)
    }

    private fun safelyCastToLayananEntityList(rawServices: Serializable?): ArrayList<LayananEntity> {
        return if (rawServices is List<*>) {
            rawServices.filterIsInstance<LayananEntity>() as ArrayList<LayananEntity>
        } else {
            arrayListOf() // Empty list if casting fails
        }
    }

    private fun displaySelectedServicesAndPrice(selectedServices: ArrayList<LayananEntity>) {
//        LinearLayout for services in CheckoutBengkelActivity
        val servicesLayout = binding.checkoutBengkel.llDynamicServices
        servicesLayout.removeAllViews()

        val subtotalPriceView = binding.checkoutBengkel.tvSubtotalPriceService

        val adminFeeNominalView = binding.checkoutBengkel.tvNominalAdminFeeService
        val adminFee = 1000
        adminFeeNominalView.text = String.format("%s", adminFee)


        val totalPriceView = binding.checkoutBengkel.tvTotalPrice

        val inflater = LayoutInflater.from(this)
        var subtotalPrice = 0
        for (service in selectedServices) {
            val serviceView =
                inflater.inflate(R.layout.item_services_component, servicesLayout, false)
            val serviceName = serviceView.findViewById<TextView>(R.id.tv_name_service)
            val servicePrice = serviceView.findViewById<TextView>(R.id.tv_price_service)

            serviceName.text = service.item_name

            val formattedServicePrice = service.harga.currencyFormatWithoutRp()
            servicePrice.text = formattedServicePrice

            subtotalPrice += service.harga

            servicesLayout.addView(serviceView)
        }
        val formattedSubtotalPrice = subtotalPrice.currencyFormat()
        subtotalPriceView.text = formattedSubtotalPrice

        val totalPrice = subtotalPrice + adminFee
        val formattedTotalPrice = totalPrice.currencyFormat()
        totalPriceView.text = formattedTotalPrice
    }

    private fun setupTextChangeListeners() {
        binding.checkoutBengkel.titleDetailLocationEditText.addTextChangedListener { updateButtonState() }
        binding.checkoutBengkel.titleFullNameEditText.addTextChangedListener { updateButtonState() }
        binding.checkoutBengkel.titleDetailComplaintEditText.addTextChangedListener { updateButtonState() }
    }

    private fun updateButtonState() {
        val isLocationEmpty =
            binding.checkoutBengkel.titleDetailLocationEditText.text.isNullOrEmpty()
        val isNameEmpty = binding.checkoutBengkel.titleFullNameEditText.text.isNullOrEmpty()
        val isComplaintsEmpty =
            binding.checkoutBengkel.titleDetailComplaintEditText.text.isNullOrEmpty()

        // Button should be enabled only if all fields are filled
        val isButtonEnabled = !isLocationEmpty && !isNameEmpty && !isComplaintsEmpty

        // Set button color
        val buttonColor = if (isButtonEnabled) {
            getColor(R.color.cinnabar)
        } else {
            getColor(R.color.roman_silver)
        }
        binding.checkoutBengkel.btnPayCheckout.setBackgroundColor(buttonColor)
        binding.checkoutBengkel.btnPayCheckout.setTextColor(Color.WHITE)
    }

    private fun payService() {
        val payService = binding.checkoutBengkel.btnPayCheckout

        payService.setOnClickListener {
            val detailLocation = binding.checkoutBengkel.titleDetailLocationEditText.text.toString()
            val userName = binding.checkoutBengkel.titleFullNameEditText.text.toString()
            val detailComplaints =
                binding.checkoutBengkel.titleDetailComplaintEditText.text.toString()

            var isEmptyField = false
            if (detailLocation.isEmpty()) {
                binding.checkoutBengkel.titleDetailLocationEditText.error = "Field harus diisi"
                isEmptyField = true
            }

            if (userName.isEmpty()) {
                binding.checkoutBengkel.titleFullNameEditText.error = "Field harus diisi"
                isEmptyField = true
            }

            if (detailComplaints.isEmpty()) {
                binding.checkoutBengkel.titleDetailComplaintEditText.error = "Field harus diisi"
                isEmptyField = true
            }

            if (!isEmptyField) {
                val selectedServices: ArrayList<LayananEntity> =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // For newer SDKs (Adjust the SDK version as per your requirement)
                        val rawServices = intent.getSerializableExtra(SELECTED_SERVICES)
                        safelyCastToLayananEntityList(rawServices)
                    } else {
                        // For older SDKs
                        @Suppress("DEPRECATION") val rawServices =
                            intent.getSerializableExtra(SELECTED_SERVICES)
                        safelyCastToLayananEntityList(rawServices)
                    }

                val totalPrice = selectedServices.sumOf { it.harga } + 1000

// Building the string with all services
                val stringBuilder = StringBuilder()
                for (service in selectedServices) {
                    stringBuilder.append("Layanan: ${service.item_name} \nHarga: ${service.harga}\n")
                }

// Create the complete message
                val completeMessage =
                    "Nama: $userName \nLokasi: $detailLocation \nKeluhan: $detailComplaints \n\n${stringBuilder.toString() + "Total: $totalPrice"}"

// Create and start the intent
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, completeMessage)
                }
                startActivity(intent)
            }
        }
    }


    companion object {
        const val DETAIL_LOCATION = "key_location"
        const val USER_NAME = "key_name"
        const val DETAIL_COMPLAINT = "key_complaint"
        const val SELECTED_SERVICES = "key_selected_services"
    }
}