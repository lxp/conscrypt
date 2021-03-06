/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.conscrypt;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

/**
 * Helper to initialize the JNI libraries. This version runs when compiled
 * as part of the platform.
 *
 * @hide
 */
@Internal
public final class InternalUtil {
    public static PublicKey logKeyToPublicKey(byte[] logKey) throws NoSuchAlgorithmException {
        return new OpenSSLKey(NativeCrypto.d2i_PUBKEY(logKey)).getPublicKey();
    }

    public static PublicKey readPublicKeyPem(InputStream pem) throws InvalidKeyException, NoSuchAlgorithmException {
        return OpenSSLKey.fromPublicKeyPemInputStream(pem).getPublicKey();
    }

    public static byte[] getOcspSingleExtension(
            byte[] ocspResponse, String oid, long x509Ref, long issuerX509Ref) {
        return NativeCrypto.get_ocsp_single_extension(ocspResponse, oid, x509Ref, issuerX509Ref);
    }

    private InternalUtil() {
    }
}
