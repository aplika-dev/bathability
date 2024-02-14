package dev.aplika.core.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

sealed class BathabilityDatastoreKey<T>(internal val key: Preferences.Key<T>) {

    object IsFirstAccess : BathabilityDatastoreKey<Boolean>(key = booleanPreferencesKey(name = "is_first_access"))

}