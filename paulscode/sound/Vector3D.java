/*
 * Decompiled with CFR 0.152.
 */
package paulscode.sound;

public class Vector3D {
    public float x;
    public float y;
    public float z;

    public Vector3D() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3D(float nx2, float ny, float nz2) {
        this.x = nx2;
        this.y = ny;
        this.z = nz2;
    }

    public Vector3D clone() {
        return new Vector3D(this.x, this.y, this.z);
    }

    public Vector3D cross(Vector3D A2, Vector3D B2) {
        return new Vector3D(A2.y * B2.z - B2.y * A2.z, A2.z * B2.x - B2.z * A2.x, A2.x * B2.y - B2.x * A2.y);
    }

    public Vector3D cross(Vector3D B2) {
        return new Vector3D(this.y * B2.z - B2.y * this.z, this.z * B2.x - B2.z * this.x, this.x * B2.y - B2.x * this.y);
    }

    public float dot(Vector3D A2, Vector3D B2) {
        return A2.x * B2.x + A2.y * B2.y + A2.z * B2.z;
    }

    public float dot(Vector3D B2) {
        return this.x * B2.x + this.y * B2.y + this.z * B2.z;
    }

    public Vector3D add(Vector3D A2, Vector3D B2) {
        return new Vector3D(A2.x + B2.x, A2.y + B2.y, A2.z + B2.z);
    }

    public Vector3D add(Vector3D B2) {
        return new Vector3D(this.x + B2.x, this.y + B2.y, this.z + B2.z);
    }

    public Vector3D subtract(Vector3D A2, Vector3D B2) {
        return new Vector3D(A2.x - B2.x, A2.y - B2.y, A2.z - B2.z);
    }

    public Vector3D subtract(Vector3D B2) {
        return new Vector3D(this.x - B2.x, this.y - B2.y, this.z - B2.z);
    }

    public float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public void normalize() {
        double t2 = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.x = (float)((double)this.x / t2);
        this.y = (float)((double)this.y / t2);
        this.z = (float)((double)this.z / t2);
    }

    public String toString() {
        return "Vector3D (" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}

