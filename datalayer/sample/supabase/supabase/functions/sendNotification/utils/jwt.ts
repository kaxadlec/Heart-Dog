export class JWTUtil {
    private static encodeBase64Url(buffer: ArrayBuffer): string {
        return btoa(String.fromCharCode(...new Uint8Array(buffer)))
            .replace(/=/g, '')
            .replace(/\+/g, '-')
            .replace(/\//g, '_');
    }

    private static async importPrivateKey(privateKeyPem: string) {
        const pemContents = privateKeyPem
            .replace('-----BEGIN PRIVATE KEY-----', '')
            .replace('-----END PRIVATE KEY-----', '')
            .replace(/\s/g, '');

        const binaryKey = Uint8Array.from(atob(pemContents), (c) => c.charCodeAt(0));

        return await crypto.subtle.importKey(
            'pkcs8',
            binaryKey,
            {
                name: 'RSASSA-PKCS1-v1_5',
                hash: { name: 'SHA-256' },
            },
            false,
            ['sign'],
        );
    }

    private static async createSignature(signatureInput: string, privateKey: CryptoKey): Promise<string> {
        const encoder = new TextEncoder();
        const signature = await crypto.subtle.sign(
            { name: 'RSASSA-PKCS1-v1_5' },
            privateKey,
            encoder.encode(signatureInput),
        );
        return this.encodeBase64Url(signature);
    }

    public static async createFirebaseJWT(clientEmail: string, privateKeyPem: string): Promise<string> {
        try {
            const now = Math.floor(Date.now() / 1000);
            const header = {
                alg: 'RS256',
                typ: 'JWT',
            };

            const payload = {
                iss: clientEmail,
                sub: clientEmail,
                aud: 'https://oauth2.googleapis.com/token',
                iat: now,
                exp: now + 3600,
                scope: 'https://www.googleapis.com/auth/firebase.messaging',
            };

            const encodedHeader = this.encodeBase64Url(new TextEncoder().encode(JSON.stringify(header)));
            const encodedPayload = this.encodeBase64Url(new TextEncoder().encode(JSON.stringify(payload)));

            const signatureInput = `${encodedHeader}.${encodedPayload}`;
            const privateKey = await this.importPrivateKey(privateKeyPem);
            const signature = await this.createSignature(signatureInput, privateKey);

            return `${signatureInput}.${signature}`;
        } catch (error) {
            console.error('JWT Creation Error:', error);
            throw new Error(`Failed to create JWT: ${error.message}`);
        }
    }
}
