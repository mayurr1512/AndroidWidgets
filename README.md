# 📱 Android Widgets – Traditional vs Glance

This project demonstrates the basics of Android App Widgets using both:

- 🧱 Traditional XML-based Widgets
- ✨ Jetpack Glance Widgets (Modern Compose-style Widgets)

It’s perfect for developers who want to explore both approaches side-by-side.

## 🚀 What’s Inside

- 🔸 Traditional App Widget using `AppWidgetProvider` and XML layouts
- 🔹 Modern Glance App Widget using Jetpack Glance + Compose
- ⚡️ Examples of click interactions and widget updates
- 💡 Code to understand when to use which type of widget

## 🛠️ Dependencies

### Core Libraries

- `androidx.glance:glance-appwidget:1.0.0`
- `androidx.compose` libraries for Glance
- `androidx.appcompat`, `material`, and `core-ktx`

Check [`libs.versions.toml`](gradle/libs.versions.toml) for version management.

## 🧩 Click Actions in Glance

This project includes usage of:

- `actionStartActivity<T>()` – Open an Activity
- `actionStartActivity(Intent)` – Open Activity with intent extras
- `actionRunCallback<T>()` – Run background logic like refreshing data

> ✅ All interaction examples follow the official [Glance documentation](https://developer.android.com/develop/ui/compose/glance/user-interaction)


## 📍 When to Use Which?

| Use Traditional Widgets | Use Glance Widgets |
|-------------------------|--------------------|
| Need full system compatibility | Prefer modern Compose-like syntax |
| Complex remote views with legacy code | Want Compose-like API & simplicity |
| Don’t want Compose dependencies | Already using Jetpack Compose |
| Need advanced support (e.g., RemoteViewsFactory) | Lightweight UI and simple actions |


## ❌ Glance Limitations (as of 1.0.0)

- Limited widget customization APIs
- No RemoteViewsFactory equivalent
- Not all Compose components are supported
- Gesture interactions like long press/swipe aren't supported

## 📚 Resources

* **Medium Article:** [Widgets in Android - The Simple Guide]([https://medium.com/p/9ae7352ad969/edit](https://medium.com/p/c5bb95853d6b/edit))

---

> If you liked this repo or found it helpful, please ⭐ it and share it with others!