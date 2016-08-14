package esdemo

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.UserType

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

class JsonUserType implements UserType {
    int[] sqlTypes() {
        [Types.LONGVARCHAR] as int[]
    }

    Class returnedClass() {
        JSONObject
    }

    boolean equals(a, b) {
        a == b
    }

    int hashCode(a) {
        a.hashCode()
    }

    @Override
    Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        String str = resultSet.getString(strings[0])
        str ? JSON.parse(str) : null
    }

    @Override
    void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, sqlTypes()[0])
        } else {
            JSONElement json = o as JSONElement
            preparedStatement.setString(i, json.toString())
        }
    }

    Object deepCopy(Object o) {
        o
    }

    Serializable disassemble(Object o) {
        o
    }

    Object assemble(Serializable cached, Object owner) {
        cached
    }

    Object replace(Object original, Object target, Object owner) {
        original
    }

    boolean isMutable() {
        false
    }
}
