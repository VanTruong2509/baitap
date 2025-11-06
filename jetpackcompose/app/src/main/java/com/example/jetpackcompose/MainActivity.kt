import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
// Đã thêm các import còn thiếu cho các icons mở rộng
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.filled.TextFields

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardOptions // Import này cũng cần cho KeyboardType

// Giả định thư viện Coil/AsyncImage có sẵn cho Image loading
// Import mock cho ví dụ
// import coil.compose.AsyncImage

// 1. Định nghĩa các màn hình/trạng thái điều hướng
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object ComponentList : Screen("component_list")
    object TextDetail : Screen("text_detail")
    object ImageDetail : Screen("image_detail")
    object TextFieldDetail : Screen("textfield_detail")
    object RowDetail : Screen("row_detail")
}

data class ComponentItem(
    val title: String,
    val description: String,
    val screen: Screen,
    val isHeader: Boolean = false,
    val icon: ImageVector? = null
)

@Composable
fun App() {
    // Trạng thái điều hướng đơn giản
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Welcome) }

    // Sử dụng MaterialTheme cơ bản
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF1E88E5), // Blue 600
            secondary = Color(0xFF42A5F5), // Blue 400
            surface = Color.White,
            background = Color.White,
            onSurface = Color.Black
        )
    ) {
        // Khối chính để chuyển đổi màn hình
        when (currentScreen) {
            Screen.Welcome -> WelcomeScreen { currentScreen = Screen.ComponentList }
            Screen.ComponentList -> ComponentListScreen(
                onNavigate = { currentScreen = it }
            )
            Screen.TextDetail -> TextDetailScreen(
                onBack = { currentScreen = Screen.ComponentList }
            )
            Screen.ImageDetail -> ImageDetailScreen(
                onBack = { currentScreen = Screen.ComponentList }
            )
            Screen.TextFieldDetail -> TextFieldDetailScreen(
                onBack = { currentScreen = Screen.ComponentList }
            )
            Screen.RowDetail -> RowDetailScreen(
                onBack = { currentScreen = Screen.ComponentList }
            )
        }
    }
}

// =========================================================
// 2. MÀN HÌNH CHÀO MỪNG (Welcome Screen)
// =========================================================

@Composable
fun WelcomeScreen(onReadyClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onReadyClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
                ) {
                    Text("I'm ready", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder cho Jetpack Compose Logo (giả định dùng Icon)
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF00C853), RoundedCornerShape(20.dp)), // Màu xanh lá
                contentAlignment = Alignment.Center
            ) {
                // Thêm một khối màu xanh dương/trắng để mô phỏng hình khối
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFF1E88E5), RoundedCornerShape(10.dp))
                )
            }

            Spacer(Modifier.height(48.dp))

            Text(
                "Jetpack Compose",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                lineHeight = 22.sp,
                modifier = Modifier.fillMaxWidth(0.8f) // Giới hạn chiều rộng văn bản
            )
        }
    }
}

// =========================================================
// 3. MÀN HÌNH DANH SÁCH (Component List)
// =========================================================

@Composable
fun ComponentListScreen(onNavigate: (Screen) -> Unit) {
    val componentItems = remember {
        listOf(
            ComponentItem("Display", "", Screen.ComponentList, isHeader = true),
            ComponentItem("Text", "Displays text", Screen.TextDetail, Icons.Default.SortByAlpha),
            ComponentItem("Image", "Displays an image", Screen.ImageDetail, Icons.Default.Image),
            ComponentItem("Input", "", Screen.ComponentList, isHeader = true),
            ComponentItem("TextField", "Input field for text", Screen.TextFieldDetail, Icons.Default.TextFields),
            ComponentItem("PasswordField", "Input field for passwords", Screen.TextFieldDetail, Icons.Default.Lock), // Dẫn đến cùng màn hình demo TextField
            ComponentItem("Layout", "", Screen.ComponentList, isHeader = true),
            ComponentItem("Column", "Arranges elements vertically", Screen.ComponentList, null),
            ComponentItem("Row", "Arranges elements horizontally", Screen.RowDetail, null),
            ComponentItem("Tự tìm hiểu", "", Screen.ComponentList, isHeader = true),
            ComponentItem("Tìm hiểu thêm", "Tìm ra tất cả các thành phần UI cơ bản", Screen.RowDetail, null), // Dẫn đến RowDetail để demo
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Components List", fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(componentItems) { item ->
                if (item.isHeader) {
                    Text(
                        item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                    )
                } else {
                    ComponentListItem(item) {
                        if (item.screen != Screen.ComponentList) {
                            onNavigate(item.screen)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentListItem(item: ComponentItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color(0xFFE3F2FD), RoundedCornerShape(8.dp)) // Light blue background
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            if (item.description.isNotEmpty()) {
                Text(item.description, color = Color.Gray, fontSize = 14.sp)
            }
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Navigate",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
    }
}

// =========================================================
// 4. MÀN HÌNH CHI TIẾT TEXT (Text Detail)
// =========================================================

@Composable
fun TextDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { DetailAppBar("Text Detail", onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text mẫu theo hình ảnh
            Text(
                text = buildAnnotatedString {
                    append("The quick ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, color = Color(0xFF6D4C41))) { // Brown color
                        append("Brown")
                    }
                    append(" fox jumps ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over")
                    }
                    append(" the ")
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Cursive)) {
                        append("lazy")
                    }
                    append(" dog.")
                },
                fontSize = 28.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

// =========================================================
// 5. MÀN HÌNH CHI TIẾT IMAGE (Image Detail)
// =========================================================

@Composable
fun ImageDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { DetailAppBar("Images", onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Image 1: Placeholder cho URL ---
            Text("Image from URL", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFB0BEC5), RoundedCornerShape(8.dp)), // Light gray placeholder
                contentAlignment = Alignment.Center
            ) {
                // Giả lập AsyncImage: Bạn cần dùng thư viện như Coil hoặc Glide trong dự án thực tế
                Text("https://example.com/image_ut.jpg", color = Color.DarkGray)
            }

            Spacer(Modifier.height(24.dp))

            // --- Image 2: Placeholder cho In-app Image ---
            Text("Image In-app", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)), // White placeholder
                contentAlignment = Alignment.Center
            ) {
                Text("In-app: res/drawable/building.png", color = Color.DarkGray)
            }
        }
    }
}

// =========================================================
// 6. MÀN HÌNH TEXTFIELD (TextField Detail)
// =========================================================

@Composable
fun TextFieldDetailScreen(onBack: () -> Unit) {
    var textValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = { DetailAppBar("TextField", onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // --- TextField thường ---
            Text("Thông tin nhập", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Nhập dữ liệu vào đây") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(Modifier.height(24.dp))

            // --- Password Field ---
            Text("Mật khẩu", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(bottom = 8.dp))
            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = { Text("Nhập mật khẩu") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(Modifier.height(32.dp))

            // --- Hiển thị kết quả (giống hình ảnh) ---
            Text(
                "Dữ liệu cập nhật từ textfield:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                if (textValue.isNotEmpty()) textValue else "...",
                color = Color.Gray
            )
        }
    }
}

// =========================================================
// 7. MÀN HÌNH ROW/LAYOUT (Row Layout Detail)
// =========================================================

@Composable
fun RowDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { DetailAppBar("Row Layout", onBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Mô phỏng grid layout bằng cách lồng Row và Column
            GridDemo(rows = 3, cols = 3)
        }
    }
}

@Composable
fun GridDemo(rows: Int, cols: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tạo các hàng
        repeat(rows) { rowIndex ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tạo các cột trong mỗi hàng
                repeat(cols) { colIndex ->
                    GridCell(rowIndex, colIndex, cols)
                }
            }
        }
    }
}

@Composable
fun RowScope.GridCell(rowIndex: Int, colIndex: Int, cols: Int) {
    // Logic tạo màu: Xanh dương nhạt và đậm xen kẽ
    val isLight = (colIndex + rowIndex) % 2 == 0
    val color = if (isLight) Color(0xFF90CAF9) else Color(0xFF42A5F5) // Blue 300 vs Blue 400

    // Sử dụng Modifier.weight() để chia đều không gian theo chiều ngang (Row)
    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(1f) // Giữ hình vuông
            .padding(horizontal = 4.dp)
            .background(color, RoundedCornerShape(8.dp))
            .clickable { /* Handle click */ },
        contentAlignment = Alignment.Center
    ) {
        // Text(color.toString(), fontSize = 10.sp) // Có thể thêm text debug
    }
}


// =========================================================
// 8. COMPOSTABLE CHUNG (Helper Composable)
// =========================================================

@Composable
fun DetailAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(title, fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}
