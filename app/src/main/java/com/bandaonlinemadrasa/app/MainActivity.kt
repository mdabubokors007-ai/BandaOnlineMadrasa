package com.bandaonlinemadrasa.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bandaonlinemadrasa.app.auth.AuthGate
import com.bandaonlinemadrasa.app.auth.AuthSessionManager
import com.bandaonlinemadrasa.app.data.AuthRepository
import kotlinx.coroutines.launch

private enum class Screen { HOME, SEARCH, FAVORITES, PROFILE, DETAILS }
private data class DemoItem(val title:String,val desc:String,val cat:String,val icon:String)
private val demoItems=listOf(
    DemoItem("নতুন ইসলামিক ছবি","ইসলামিক শিক্ষামূলক ছবি ও পোস্টার","Image","🖼️"),
    DemoItem("জুমুয়ার বয়ান","গুরুত্বপূর্ণ দ্বীনি আলোচনা ও বয়ান","Video","▶️"),
    DemoItem("শিক্ষামূলক সফটওয়্যার","উপকারী সফটওয়্যার ও প্রয়োজনীয় টুল","Software","💻"),
    DemoItem("ইসলামিক বই ও ডকুমেন্ট","PDF, Word এবং অন্যান্য নথি","Document","📄")
)

class MainActivity : ComponentActivity() {
    private val sessionManager by lazy { AuthSessionManager(SupabaseClientProvider.client) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SupabaseClientProvider.client.handleDeeplinks(intent)
        setContent { BandaApp(sessionManager) }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        SupabaseClientProvider.client.handleDeeplinks(intent)
    }
}

@Composable private fun BandaApp(sessionManager: AuthSessionManager) = AuthGate(
    sessionManager = sessionManager,
    loginContent = { LoginScreen() },
    authenticatedContent = { userId -> LibraryScreen(sessionManager, userId) }
)

@Composable private fun LoginScreen() {
    val scope=rememberCoroutineScope(); var busy by remember{mutableStateOf(false)}; var error by remember{mutableStateOf<String?>(null)}
    Column(Modifier.fillMaxSize().padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){
        Image(painterResource(R.drawable.app_logo),"বান্দা অনলাইন মাদরাসা",Modifier.size(190.dp))
        Spacer(Modifier.height(14.dp)); Text("বান্দা অনলাইন মাদরাসা",fontSize=28.sp,fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(7.dp)); Text("জ্ঞান অর্জন হোক সহজ, আমল হোক জীবনের আলো।",fontSize=15.sp)
        Spacer(Modifier.height(26.dp)); Button(enabled=!busy,onClick={error=null;busy=true;scope.launch{try{AuthRepository().signInWithGoogle()}catch(t:Throwable){error=t.message?:"Google লগইন শুরু করা যায়নি";busy=false}}},Modifier.fillMaxWidth()){Text(if(busy)"Google লগইন খোলা হচ্ছে…" else "Continue with Google")}
        if(error!=null){Spacer(Modifier.height(10.dp));Text(error!!,color=MaterialTheme.colorScheme.error,fontSize=12.sp)}
        Spacer(Modifier.height(12.dp));Text("লগইনের জন্য ইন্টারনেট ও Supabase/Google configuration প্রয়োজন।",fontSize=11.sp)
    }
}

@Composable private fun LibraryScreen(session:AuthSessionManager,userId:String){
    var screen by remember{mutableStateOf(Screen.HOME)};var selected by remember{mutableStateOf<DemoItem?>(null)};var query by remember{mutableStateOf("")}
    when(screen){
        Screen.HOME->Home({screen=Screen.SEARCH},{selected=it;screen=Screen.DETAILS},{screen=Screen.FAVORITES},{screen=Screen.PROFILE})
        Screen.SEARCH->Search(query,{query=it},{screen=Screen.HOME},{selected=it;screen=Screen.DETAILS})
        Screen.FAVORITES->Page("⭐ পছন্দের তালিকা","আপনার পছন্দের কনটেন্ট এখানে থাকবে।"){screen=Screen.HOME}
        Screen.PROFILE->ProfilePage(session,userId){screen=Screen.HOME}
        Screen.DETAILS->selected?.let{Details(it){screen=Screen.HOME}}
    }
}

@Composable private fun Home(search:()->Unit,select:(DemoItem)->Unit,fav:()->Unit,profile:()->Unit)=Scaffold(bottomBar={NavigationBar{NavigationBarItem(true,{},icon={Text("⌂")},label={Text("হোম")});NavigationBarItem(false,search,icon={Text("⌕")},label={Text("খুঁজুন")});NavigationBarItem(false,fav,icon={Text("★")},label={Text("পছন্দ")});NavigationBarItem(false,profile,icon={Text("●")},label={Text("প্রোফাইল")})}}){p->LazyColumn(Modifier.padding(p).padding(18.dp)){item{Spacer(Modifier.height(18.dp));Text("☪️  বান্দা অনলাইন মাদরাসা",fontSize=23.sp,fontWeight=FontWeight.Bold);Text("জ্ঞান অর্জন হোক সহজ, আমল হোক জীবনের আলো।",fontSize=13.sp);Spacer(Modifier.height(16.dp));OutlinedButton(search,Modifier.fillMaxWidth()){Text("🔎  নাম, বর্ণনা বা ক্যাটাগরি খুঁজুন")};Spacer(Modifier.height(20.dp));Text("ক্যাটাগরি",fontSize=20.sp,fontWeight=FontWeight.Bold)};items(demoItems){x->Card(Modifier.fillMaxWidth().padding(vertical=6.dp).clickable{select(x)}){Row(Modifier.padding(18.dp),verticalAlignment=Alignment.CenterVertically){Text(x.icon,fontSize=30.sp);Spacer(Modifier.width(15.dp));Column(Modifier.weight(1f)){Text(x.title,fontWeight=FontWeight.Bold);Text(x.cat,fontSize=12.sp);Text(x.desc,fontSize=13.sp)};Text("›",fontSize=28.sp)}}};item{Spacer(Modifier.height(18.dp));Text("সাম্প্রতিক আপলোড",fontSize=20.sp,fontWeight=FontWeight.Bold);Text("নতুন ছবি • ভিডিও • সফটওয়্যার • ডকুমেন্ট এখানে দেখা যাবে।",fontSize=13.sp)}}}

@Composable private fun Search(q:String,set:(String)->Unit,back:()->Unit,select:(DemoItem)->Unit){val r=demoItems.filter{x->q.isBlank()||"${x.title} ${x.desc} ${x.cat}".contains(q,true)};Column(Modifier.fillMaxSize().padding(18.dp)){TextButton(back){Text("‹ ফিরে")};Text("খুঁজুন",fontSize=25.sp,fontWeight=FontWeight.Bold);OutlinedTextField(q,set,Modifier.fillMaxWidth(),placeholder={Text("কী খুঁজছেন?")});LazyColumn{items(r){x->Card(Modifier.fillMaxWidth().padding(5.dp).clickable{select(x)}){Row(Modifier.padding(16.dp)){Text(x.icon,fontSize=26.sp);Spacer(Modifier.width(12.dp));Column{Text(x.title,fontWeight=FontWeight.Bold);Text(x.desc,fontSize=13.sp)}}}}}}}

@Composable private fun Details(x:DemoItem,back:()->Unit)=Column(Modifier.fillMaxSize().padding(22.dp)){TextButton(back){Text("‹ ফিরে")};Spacer(Modifier.height(20.dp));Text(x.icon,fontSize=60.sp);Text(x.title,fontSize=26.sp,fontWeight=FontWeight.Bold);Text(x.cat,fontSize=13.sp);Spacer(Modifier.height(12.dp));Text(x.desc,fontSize=16.sp);Spacer(Modifier.height(25.dp));Button({},Modifier.fillMaxWidth()){Text("ডাউনলোড")};OutlinedButton({},Modifier.fillMaxWidth()){Text("শেয়ার")}}

@Composable private fun ProfilePage(session:AuthSessionManager,userId:String,back:()->Unit){val scope=rememberCoroutineScope();Column(Modifier.fillMaxSize().padding(22.dp)){TextButton(back){Text("‹ হোমে ফিরে যান")};Spacer(Modifier.height(25.dp));Text("👤 প্রোফাইল",fontSize=27.sp,fontWeight=FontWeight.Bold);Spacer(Modifier.height(12.dp));Text("Account connected",fontSize=16.sp);Text("ID: ${userId.take(8)}…",fontSize=12.sp);Spacer(Modifier.height(24.dp));Button({}){Text("Settings")};OutlinedButton({scope.launch{session.signOut()}},Modifier.fillMaxWidth()){Text("Logout")}}}
@Composable private fun Page(t:String,b:String,back:()->Unit)=Column(Modifier.fillMaxSize().padding(22.dp)){TextButton(back){Text("‹ হোমে ফিরে যান")};Spacer(Modifier.height(25.dp));Text(t,fontSize=27.sp,fontWeight=FontWeight.Bold);Spacer(Modifier.height(12.dp));Text(b,fontSize=16.sp)}
