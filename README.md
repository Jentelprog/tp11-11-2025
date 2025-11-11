# 💬 Mascot Chat App

A simple Android chatbot-style app built with **Java** and **Android Studio**.  
It uses multiple activities to collect a user’s name, display chat messages,  
and send messages between screens using **Intents** and **startActivityForResult**.

---

## 🚀 Current Features

### 1️⃣ Login Screen (`LoginActivity`)
- The first screen the user sees.
- User types their **name** and taps **Continue**.
- The name is sent to the main chat screen using an **Intent** and a **Bundle**.

**Key Concepts Used:**
- `Intent` to start a new activity.
- `Bundle` to pass data between activities.
- `Toast` for user-friendly messages.
- `InputMethodManager` to hide the keyboard.

---

### 2️⃣ Main Chat Screen (`MainActivity`)
- Greets the user with “Hello, <name> 👋”.
- Displays **all chat messages** using a `ListView`.
- When you type a message and hit **Send**, it:
  1. Starts the **EchoActivity** using `startActivityForResult`.
  2. Waits for the result (the same message returned).
  3. Adds that message to the chat list.

**Key Concepts Used:**
- `ListView` to show all messages.
- `ArrayList<String>` to store messages.
- `ArrayAdapter<String>` to connect the data to the ListView.
- `startActivityForResult` to get a result from another activity.
- `onActivityResult` to receive data back.
- `onSaveInstanceState` to keep messages after rotation.

---

### 3️⃣ Echo Screen (`EchoActivity`)
- Receives a message from MainActivity.
- Displays it in a simple preview layout.
- Has two buttons:
  - **Return:** sends the same text back to MainActivity.
  - **Cancel:** closes without returning data.

**Key Concepts Used:**
- Reading and returning data with `Intent` extras.
- Using `setResult()` and `finish()` to send results back.
- Understanding variable scope with lambdas (Java’s “final variable” rule).

---

## 🧩 App Flow Summary

1️⃣ **LoginActivity** → user enters their name → starts **MainActivity**  
2️⃣ **MainActivity**:
   - Receives the name.
   - Shows greeting in ListView.
   - Waits for user messages.
3️⃣ User types a message → opens **EchoActivity** with that message.  
4️⃣ **EchoActivity** returns the same message.  
5️⃣ **MainActivity** adds it to the message list → UI updates.

---

## 🗂 Project Structure

