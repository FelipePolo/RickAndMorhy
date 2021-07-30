package com.felipepolo.pruebaenvivo.utils

import android.content.Context
import android.widget.Toast

fun Context.showErrorToast(error: String) {
    Toast.makeText(this,"ERROR: ${error}",Toast.LENGTH_SHORT).show()
}

fun Context.showToast(msj: String) {
    Toast.makeText(this,msj,Toast.LENGTH_SHORT).show()
}
