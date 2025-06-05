
import React, { useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { ArrowLeft, MapPin, Clock, Star, Users, Info, CheckCircle, Phone, Navigation } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';

const mockOffers = [
  {
    id: '1',
    title: 'Fresh Bread & Pastries Mix',
    restaurant: 'Bella Vista Bakery',
    partnerId: 'bella-vista',
    originalPrice: 85,
    discountedPrice: 25,
    distance: '0.3km',
    timeLeft: '2h left',
    image: 'photo-1555507036-ab794f4aacd3',
    category: 'Bakery',
    pickupTime: 'Today 18:00-20:00',
    rating: 4.8,
    description: 'Assorted fresh pastries including croissants, danish, and fresh bread baked this morning. Perfect for breakfast or a family treat!',
    items: ['Croissants (2-3 pieces)', 'Danish pastries (2-3 pieces)', 'Fresh bread (1 loaf)', 'Muffins (2-3 pieces)'],
    address: 'Strada Kogălniceanu 67, Chișinău',
    phone: '+373 22 123 456',
    availableQuantity: 3,
    allergens: ['Gluten', 'Eggs', 'Dairy'],
    pickupInstructions: 'Please come to the main counter and mention your EcoMeal reservation.',
    coordinates: { lat: 47.0245, lng: 28.8322 }
  },
];

const OfferDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [isReserved, setIsReserved] = useState(false);
  
  const offer = mockOffers.find(o => o.id === id);

  if (!offer) {
    return (
      <div className="min-h-screen bg-eco-bg flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 mb-4">Offer not found</h2>
          <Link to="/marketplace">
            <Button className="bg-green-500 hover:bg-green-600">Back to Marketplace</Button>
          </Link>
        </div>
      </div>
    );
  }

  const discountPercentage = Math.round(((offer.originalPrice - offer.discountedPrice) / offer.originalPrice) * 100);

  const handleReserve = () => {
    setIsReserved(true);
  };

  const openInMaps = () => {
    const url = `https://www.google.com/maps?q=${offer.coordinates.lat},${offer.coordinates.lng}`;
    window.open(url, '_blank');
  };

  return (
    <div className="min-h-screen bg-eco-bg">
      {/* Header */}
      <div className="bg-white shadow-sm sticky top-0 z-40">
        <div className="max-w-4xl mx-auto px-4 py-4">
          <Button 
            variant="ghost" 
            onClick={() => navigate(-1)}
            className="mb-4 hover:bg-green-50"
          >
            <ArrowLeft size={20} className="mr-2" />
            Back
          </Button>
        </div>
      </div>

      <div className="max-w-4xl mx-auto px-4 py-6">
        {/* Main Image */}
        <div className="relative mb-6">
          <img
            src={`https://images.unsplash.com/${offer.image}?w=800&h=400&fit=crop`}
            alt={offer.title}
            className="w-full h-80 object-cover rounded-2xl shadow-lg"
          />
          <div className="absolute top-4 left-4 bg-red-500 text-white px-4 py-2 rounded-full font-bold text-lg">
            -{discountPercentage}%
          </div>
          <div className="absolute top-4 right-4 bg-white/90 backdrop-blur-sm rounded-full px-4 py-2 flex items-center gap-2 shadow-lg">
            <Star size={18} className="fill-yellow-400 text-yellow-400" />
            <span className="font-medium">{offer.rating}</span>
          </div>
        </div>

        <div className="grid lg:grid-cols-3 gap-8">
          {/* Main Content */}
          <div className="lg:col-span-2 space-y-6">
            {/* Title and Restaurant */}
            <Card className="border-0 shadow-md">
              <CardContent className="p-6">
                <h1 className="text-3xl font-bold text-gray-900 mb-3">{offer.title}</h1>
                <Link 
                  to={`/partner/${offer.partnerId}`}
                  className="text-xl text-green-500 hover:text-green-600 font-medium mb-4 inline-block"
                >
                  {offer.restaurant}
                </Link>
                <div className="flex items-center gap-4 text-gray-600">
                  <div className="flex items-center gap-2">
                    <MapPin size={18} />
                    {offer.distance}
                  </div>
                  <Badge variant="outline" className="bg-green-50 text-green-700 border-green-200">
                    {offer.category}
                  </Badge>
                </div>
              </CardContent>
            </Card>

            {/* Description */}
            <Card className="border-0 shadow-md">
              <CardContent className="p-6">
                <h3 className="text-xl font-semibold mb-4 text-gray-900">About this offer</h3>
                <p className="text-gray-700 mb-6 leading-relaxed">{offer.description}</p>
                
                <h4 className="font-semibold mb-3 text-gray-900">What's included:</h4>
                <ul className="space-y-2">
                  {offer.items.map((item, index) => (
                    <li key={index} className="flex items-center gap-3 text-gray-700">
                      <CheckCircle size={18} className="text-green-500 flex-shrink-0" />
                      {item}
                    </li>
                  ))}
                </ul>
              </CardContent>
            </Card>

            {/* Location & Map */}
            <Card className="border-0 shadow-md">
              <CardContent className="p-6">
                <div className="flex items-center gap-3 mb-4">
                  <MapPin size={24} className="text-green-500" />
                  <h3 className="text-xl font-semibold text-gray-900">Pickup Location</h3>
                </div>
                
                <div className="mb-4">
                  <p className="text-gray-700 mb-2">{offer.address}</p>
                  <div className="flex items-center gap-2 text-gray-600">
                    <Phone size={16} />
                    <span>{offer.phone}</span>
                  </div>
                </div>

                {/* Map placeholder - In a real app, you'd integrate with Google Maps or similar */}
                <div className="relative bg-gray-100 rounded-lg h-64 mb-4 overflow-hidden">
                  <img 
                    src={`https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/pin-s-restaurant+green(${offer.coordinates.lng},${offer.coordinates.lat})/${offer.coordinates.lng},${offer.coordinates.lat},15,0/600x300@2x?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw`}
                    alt="Map location"
                    className="w-full h-full object-cover"
                  />
                  <div className="absolute inset-0 bg-gradient-to-t from-black/20 to-transparent pointer-events-none"></div>
                </div>

                <Button 
                  onClick={openInMaps}
                  variant="outline" 
                  className="w-full border-green-500 text-green-500 hover:bg-green-50"
                >
                  <Navigation size={18} className="mr-2" />
                  Open in Maps
                </Button>
              </CardContent>
            </Card>

            {/* Allergens */}
            <Card className="border-0 shadow-md">
              <CardContent className="p-6">
                <div className="flex items-center gap-3 mb-4">
                  <Info size={22} className="text-orange-500" />
                  <h3 className="text-xl font-semibold text-gray-900">Allergen Information</h3>
                </div>
                <div className="flex flex-wrap gap-2">
                  {offer.allergens.map((allergen, index) => (
                    <Badge key={index} variant="outline" className="bg-orange-50 text-orange-700 border-orange-200 px-3 py-1">
                      {allergen}
                    </Badge>
                  ))}
                </div>
              </CardContent>
            </Card>

            {/* Pickup Instructions */}
            <Card className="border-0 shadow-md">
              <CardContent className="p-6">
                <h3 className="text-xl font-semibold mb-4 text-gray-900">Pickup Instructions</h3>
                <p className="text-gray-700 leading-relaxed">{offer.pickupInstructions}</p>
              </CardContent>
            </Card>
          </div>

          {/* Sidebar */}
          <div className="space-y-6">
            {/* Reservation Card */}
            <Card className="sticky top-24 border-0 shadow-lg">
              <CardContent className="p-6">
                <div className="text-center mb-6">
                  <div className="flex items-center justify-center gap-3 mb-3">
                    <span className="text-4xl font-bold text-green-500">
                      {offer.discountedPrice} lei
                    </span>
                    <span className="text-xl text-gray-500 line-through">
                      {offer.originalPrice} lei
                    </span>
                  </div>
                  <p className="text-sm text-gray-600">per portion</p>
                </div>

                <div className="space-y-4 mb-6">
                  <div className="flex items-center justify-between py-2 border-b border-gray-100">
                    <span className="text-gray-700 font-medium">Pickup time:</span>
                    <span className="font-semibold text-gray-900">{offer.pickupTime}</span>
                  </div>
                  <div className="flex items-center justify-between py-2 border-b border-gray-100">
                    <span className="text-gray-700 font-medium">Available:</span>
                    <span className="font-semibold flex items-center gap-2 text-gray-900">
                      <Users size={16} />
                      {offer.availableQuantity} left
                    </span>
                  </div>
                  <div className="flex items-center justify-between py-2">
                    <span className="text-gray-700 font-medium">Time left:</span>
                    <span className="font-semibold text-orange-600 flex items-center gap-2">
                      <Clock size={16} />
                      {offer.timeLeft}
                    </span>
                  </div>
                </div>

                {!isReserved ? (
                  <Button 
                    onClick={handleReserve}
                    className="w-full bg-green-500 hover:bg-green-600 text-white text-lg py-4 rounded-xl font-semibold"
                    size="lg"
                  >
                    Reserve Now
                  </Button>
                ) : (
                  <div className="text-center">
                    <div className="bg-green-50 border-2 border-green-200 rounded-xl p-6 mb-4">
                      <CheckCircle size={32} className="text-green-500 mx-auto mb-3" />
                      <p className="font-semibold text-green-800 text-lg">Reserved Successfully!</p>
                      <p className="text-sm text-green-700 mt-2">
                        You'll receive a confirmation shortly
                      </p>
                    </div>
                    <Button variant="outline" className="w-full border-green-500 text-green-500 hover:bg-green-50">
                      View My Reservations
                    </Button>
                  </div>
                )}

                <p className="text-xs text-gray-500 text-center mt-4">
                  Free cancellation up to 1 hour before pickup
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OfferDetail;
