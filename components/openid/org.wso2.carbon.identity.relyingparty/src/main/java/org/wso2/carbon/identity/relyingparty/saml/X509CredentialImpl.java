/*
 * Copyright (c) 2008, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.relyingparty.saml;

import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.CredentialContextSet;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.x509.X509Credential;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collection;

/**
 * X509Credential implementation for signature verification of self issued tokens. The key is
 * constructed from modulus and exponent
 */
public class X509CredentialImpl implements X509Credential {

    private PublicKey publicKey = null;
    private X509Certificate signingCert = null;

    // cert chain

    /**
     * The key is constructed form modulus and exponent.
     *
     * @param modulus
     * @param publicExponent
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public X509CredentialImpl(BigInteger modulus, BigInteger publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(spec);
    }

    public X509CredentialImpl(X509Certificate cert) {
        publicKey = cert.getPublicKey();
        signingCert = cert;
    }

    /**
     * Retrieves the publicKey
     */
    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public X509Certificate getSigningCert() {
        return signingCert;
    }

    // ********** Not implemented **************************************************************

    @Override
    public X509Certificate getEntityCertificate() {
        // Nothing to implement
        return null;
    }

    @Override
    public Collection<X509CRL> getCRLs() {
        // Nothing to implement
        return null;
    }

    @Override
    public Collection<X509Certificate> getEntityCertificateChain() {
        // Nothing to implement
        return null;
    }

    @Override
    public CredentialContextSet getCredentalContextSet() {
        // Nothing to implement
        return null;
    }

    @Override
    public Class<? extends Credential> getCredentialType() {
        // Nothing to implement
        return null;
    }

    @Override
    public String getEntityId() {
        // Nothing to implement
        return null;
    }

    @Override
    public Collection<String> getKeyNames() {
        // Nothing to implement
        return null;
    }

    @Override
    public PrivateKey getPrivateKey() {
        // Nothing to implement
        return null;
    }

    @Override
    public SecretKey getSecretKey() {
        // Nothing to implement
        return null;
    }

    @Override
    public UsageType getUsageType() {
        // Nothing to implement
        return null;
    }

}