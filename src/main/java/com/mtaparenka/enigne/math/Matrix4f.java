package com.mtaparenka.enigne.math;

import java.nio.FloatBuffer;

public class Matrix4f {
    private float m00, m10, m20, m30;
    private float m01, m11, m21, m31;
    private float m02, m12, m22, m32;
    private float m03, m13, m23, m33;

    private Matrix4f(float value) {
        //  m00   m10   m20   m30
        //  m01   m11   m21   m31
        //  m02   m12   m22   m32
        //  m03   m13   m23   m33
        m00 = value;
        m01 = 0.0f;
        m02 = 0.0f;
        m03 = 0.0f;
        m10 = 0.0f;
        m11 = value;
        m12 = 0.0f;
        m13 = 0.0f;
        m20 = 0.0f;
        m21 = 0.0f;
        m22 = value;
        m23 = 0.0f;
        m30 = 0.0f;
        m31 = 0.0f;
        m32 = 0.0f;
        m33 = 1.0f;
    }

    public static Matrix4f identity() {
        return new Matrix4f(1f);
    }

    public Matrix4f scale(float x, float y, float z) {
        //  m00   m10   m20   m30       x   0   0   0
        //  m01   m11   m21   m31   x   0   y   0   0
        //  m02   m12   m22   m32       0   0   z   0
        //  m03   m13   m23   m33       0   0   0   1

        //m00 = m00 * x + m10 * 0 + m20 * 0 + m30 * 0 = m00 * x + 0 + 0 + 0 = m00 * x
        m00 = m00*x;
        //m01 = m01 * x + m11 * 0 + m21 * 0 + m31 * 0 = m01 * x + 0 + 0 + 0 = m01 * x
        m01 = m01*x;
        //...
        m02 = m02*x;
        m03 = m03*x;
        //m10 = m00 * 0 + m10 * y + m20 * 0 + m30 * 0 = m10 * y
        m10 = m10*y;
        //m11 = m01 * 0 + m11 * y + m21 * 0 + m31 * 0 = m11 * y
        m11 = m11*y;
        m12 = m12*y;
        m13 = m13*y;
        m20 = m20*z;
        m21 = m21*z;
        m22 = m22*z;
        m23 = m23*z;
        /*m30 = m30;
        m31 = m31;
        m32 = m32;
        m33 = m33;*/
        return this;
    }

    public Matrix4f rotateZ(float degrees) {
        double rad = Math.toRadians(degrees);
        float cosA = (float) Math.cos(rad);
        float sinA = (float) Math.sin(rad);
        //  m00   m10   m20   m30       cosθ   −sinθ    0   0
        //  m01   m11   m21   m31   x   sinθ    cosθ    0   0
        //  m02   m12   m22   m32          0       0    1   0
        //  m03   m13   m23   m33          0       0    0   1

        float nm00 = m00 * cosA + m10 * sinA;
        float nm01 = m01 * cosA + m11 * sinA;
        float nm02 = m02 * cosA + m12 * sinA;
        float nm03 = m03 * cosA + m13 * sinA;
        float nm10 = m00 * -sinA + m10 * cosA;
        float nm11 = m01 * -sinA + m11 * cosA;
        float nm12 = m02 * -sinA + m12 * cosA;
        float nm13 = m03 * -sinA + m13 * cosA;

        m00=nm00;
        m01=nm01;
        m02=nm02;
        m03=nm03;
        m10=nm10;
        m11=nm11;
        m12=nm12;
        m13=nm13;

        return this;
    }

    public void get(FloatBuffer fb) {
        int position = fb.position();
        fb.put(new float[] {
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        }).position(position);
    }
}
