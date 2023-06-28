package com.aplika.core.datastore

import androidx.datastore.preferences.core.Preferences

sealed class BathabilityDatastoreKey<T>(internal val key: Preferences.Key<T>) {

}