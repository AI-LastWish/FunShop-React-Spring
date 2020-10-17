import React, { useEffect } from 'react'
import { Col, Row } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Product from '../components/Product'
import { listProducts } from '../redux/actions/productActions'

const HomeScreen = () => {
  const dispatch = useDispatch()

  const { loading, products, error } = useSelector(state => state.productList)

  useEffect(() => {
    dispatch(listProducts())
  }, [dispatch])

  return (
    <>
      <h1>Latest Products</h1>
      {loading ?
        <Loader />
        : error
          ? <Message variant='danger'>{error}</Message>
          : <Row>
            {products.map(product => (
              <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                <Product product={product} />
              </Col>
            ))}
          </Row>}

    </>
  )
}

export default HomeScreen
