package com.example.wastebin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [f_akun.newInstance] factory method to
 * create an instance of this fragment.
 */
class f_akun : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var settingsview : View = inflater.inflate(R.layout.fragment_f_akun, container, false)
        // Inflate the layout for this fragment
        var btnkeluar = settingsview.findViewById<Button>(R.id.exit)
        btnkeluar.setOnClickListener {
            showLogoutConfirmationDialog()

        }

        return settingsview }
    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setMessage("Anda yakin ingin logout?")

        // Tombol positif (ya)
        builder.setPositiveButton("Ya") { dialog, which ->
            // Tambahkan kode logout di sini
            // Contoh: Keluar dari sesi pengguna, hapus token akses, dll.
            performLogout()
        }

        // Tombol negatif (batal)
        builder.setNegativeButton("Batal") { dialog, which ->
            // Batal logout, tutup dialog
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun performLogout() {
        // Tambahkan logika logout di sini
        // Contoh: Keluar dari sesi pengguna, hapus token akses, dll.
        val intent = Intent(requireContext(), Login::class.java)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment f_akun.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            f_akun().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}