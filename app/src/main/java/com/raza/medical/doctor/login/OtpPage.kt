package com.raza.medical.doctor.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpPage(onOtpComplete: (String) -> Unit) {
    Scaffold { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 200.dp),
                text = "Verify OTP",
                fontSize = 20.sp
            )

            Text(
                modifier = Modifier
                    .padding(
                        top = 50.dp,
                        start = 50.dp,
                        end = 50.dp
                    ),
                text = "Enter the verification code sent to " +
                        "+91 8390046849",
                fontSize = 20.sp
            )


            OtpInput {

            }

            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
            ) {
                Text(
                    text =
                        "00:45"
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text =
                        "Resend OTP"
                )
            }

            Button(
                modifier = Modifier
                    .padding(top = 50.dp),
                onClick = {

                }) {
                Text(
                    text =
                        "Verify"
                )
            }
        }
    }
}

@Composable
fun OtpInput(onOtpComplete: (String) -> Unit) {
    var otp by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = Modifier
            .padding(top = 50.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = otp,
        onValueChange = {

            //otp = it

            inputOtp ->

            if (inputOtp.length <= 6 &&
                inputOtp.all { char -> char.isDigit() }
            ) {
                otp = inputOtp

                if (inputOtp.length == 6) {
                    onOtpComplete(inputOtp)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row {
                repeat(6) { index ->
                    val char = otp.getOrNull(index)?.toString()?:""

                    Box(
                        modifier = Modifier.size(48.dp)
                            .padding(1.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(
                                8.dp
                            )),
                        contentAlignment = Alignment.Center
                    ) {
                        if(char.isNotEmpty()) {
                            Text(
                                text = char,
                                fontSize = 25.sp
                            )
                        } else if(isFocused && index == otp.length) {

                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(24.dp)
                                    .background(Color.Black)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOtpPage() {
    OtpPage {

    }
}