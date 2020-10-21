import React, { useState } from 'react'
import { useEffect } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import { getUserDetails, updateUserProfile } from '../redux/actions/userActions'

const ProfileScreen = ({ history }) => {

  const [fullName, setFullName] = useState('')
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  const [message, setMessage] = useState(null)

  const dispatch = useDispatch()

  const { loading, error, user } = useSelector(state => state.userDetails)

  const { userInfo } = useSelector(state => state.userLogin)

  let success = false;

  useEffect(() => {
    if (!userInfo) {
      history.push('/login')
    } else {
      if (!user || !user.fullName) {
        dispatch(getUserDetails())
      } else {
        setFullName(user.fullName)
        setUsername(user.username)
      }
    }
  }, [dispatch, history, userInfo, user])

  const submitHandler = e => {
    e.preventDefault()
    if (password !== confirmPassword)
      setMessage('Passwords do not match')
    else {
      dispatch(updateUserProfile({ id: user.id, fullName, password }))
      success = true;
    }

  }

  return (
    <Row>
      <Col md={3}>
        <h2>User Profile</h2>
        {message && <Message variant='danger'>{message}</Message>}
        {error && <Message variant='danger'>{error}</Message>}
        {success && <Message variant='success'>Profile Updated</Message>}
        {loading && <Loader />}
        <Form onSubmit={submitHandler}>
          <Form.Group controlId='fullName'>
            <Form.Label>Full Name</Form.Label>
            <Form.Control
              type='fullName'
              placeholder='Enter Full Name'
              value={fullName}
              onChange={e => setFullName(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Form.Group controlId='email'>
            <Form.Label>Email Address</Form.Label>
            <Form.Control
              disabled
              type='email'
              placeholder='Enter email'
              value={username}
              onChange={e => setUsername(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Form.Group controlId='password'>
            <Form.Label>Password</Form.Label>
            <Form.Control
              type='password'
              placeholder='Enter password'
              value={password}
              onChange={e => setPassword(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Form.Group controlId='confirmPassword'>
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control
              type='password'
              placeholder='Confirm password'
              value={confirmPassword}
              onChange={e => setConfirmPassword(e.target.value)}
            ></Form.Control>
          </Form.Group>

          <Button type='submit' variant='primary'>Update</Button>
        </Form>
        <Col md={9}>
          <h2>My Orders</h2>
        </Col>
      </Col>
    </Row>
  )
}

export default ProfileScreen
