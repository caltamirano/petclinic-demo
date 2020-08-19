import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import {Row, Col, Button, Container } from 'reactstrap';

class Home extends Component {
  render() {
    return (
      <div>
        <AppNavbar/>
        <Container>
          <Row>
            <Col>
              <Button color="link">
              <Link to="/pets">
                <img src="/pet.png" class="img-thumbnail" alt="Pets" style={{width: "50%", height: "50%"}} />
                <h4>Pets</h4>
              </Link>
              </Button>
            </Col>
            <Col>
            <Button color="link">
              <Link to="/vets">
                <img src="/vet.png" class="img-thumbnail" alt="Vets" style={{width: "50%", height: "50%"}} />
                <h4>Vets</h4>
              </Link>
              </Button>
            </Col>
            <Col>
            <Button color="link">
              <Link to="/appointments">
                <img src="/calendar.png" class="img-thumbnail" alt="Appointments" style={{width: "50%", height: "50%"}} />
                <h4>Appointments</h4>
              </Link>
              </Button>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default Home;