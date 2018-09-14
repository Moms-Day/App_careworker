package momsday.careworker.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import momsday.careworker.R
import momsday.careworker.adapter.ChatRecyclerViewAdapter
import momsday.careworker.model.ChatRecyclerItem
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val nowDate = Date(System.currentTimeMillis())
        val timeDateFormat = SimpleDateFormat("hh:mm");
        val dateDateFormat = SimpleDateFormat("yyyy년 MM월 dd일");
        val timeText = timeDateFormat.format(nowDate);
        val dateText = dateDateFormat.format(nowDate);

        val database = FirebaseDatabase.getInstance()
        val reference = database.reference

        recycler_main_chat.layoutManager = LinearLayoutManager(this)
        val chatItem = ArrayList<ChatRecyclerItem>()
        chatItem.add(ChatRecyclerItem(0, dateText))
        chatItem.add(ChatRecyclerItem(1, dateText))
        val adapter = ChatRecyclerViewAdapter(chatItem)
        recycler_main_chat.adapter = adapter
        text_chat_send_message.onClick {
            val chatData = ChatRecyclerItem(1, timeText, edit_chat_message.text.toString())
            reference.child("message").push().setValue(chatData)
            edit_chat_message.text.clear()
        }

        reference.child("message").addChildEventListener(object : ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatData = p0.getValue(ChatRecyclerItem::class.java)  // chatData를 가져오고
                chatItem.add(chatData!!)
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }


            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}


            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}
