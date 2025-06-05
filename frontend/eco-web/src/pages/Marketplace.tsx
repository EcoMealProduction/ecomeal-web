
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { MapPin, Clock, Star, Filter, Search } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';

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
    description: 'Assorted fresh pastries including croissants, danish, and fresh bread baked this morning.',
    items: ['Croissants', 'Danish pastries', 'Fresh bread', 'Muffins']
  },
  {
    id: '2',
    title: 'Gourmet Sandwich Collection',
    restaurant: 'Green Garden Cafe',
    partnerId: 'green-garden',
    originalPrice: 120,
    discountedPrice: 40,
    distance: '0.8km',
    timeLeft: '4h left',
    image: 'photo-1554118811-1e0d58224f24',
    category: 'Café',
    pickupTime: 'Today 19:00-21:00',
    rating: 4.9,
    description: 'Premium sandwiches with organic ingredients and specialty coffee.',
    items: ['Club sandwich', 'Veggie wrap', 'Panini', 'Side salad']
  },
  {
    id: '3',
    title: 'Fresh Produce Box',
    restaurant: 'Metro Fresh Market',
    partnerId: 'metro-fresh',
    originalPrice: 95,
    discountedPrice: 35,
    distance: '1.2km',
    timeLeft: '1h left',
    image: 'photo-1578662996442-48f60103fc96',
    category: 'Supermarket',
    pickupTime: 'Today 17:30-19:30',
    rating: 4.6,
    description: 'Mix of fresh seasonal vegetables and fruits, perfect for healthy meals.',
    items: ['Seasonal vegetables', 'Fresh fruits', 'Herbs', 'Organic produce']
  },
  {
    id: '4',
    title: 'Italian Pasta & Pizza',
    restaurant: 'Pasta & More',
    partnerId: 'pasta-more',
    originalPrice: 140,
    discountedPrice: 50,
    distance: '0.9km',
    timeLeft: '3h left',
    image: 'photo-1555396273-367ea4eb4db5',
    category: 'Restaurant',
    pickupTime: 'Today 20:00-22:00',
    rating: 4.7,
    description: 'Authentic Italian dishes prepared with fresh ingredients.',
    items: ['Margherita pizza', 'Pasta carbonara', 'Caesar salad', 'Tiramisu']
  },
  {
    id: '5',
    title: 'Artisan Dessert Box',
    restaurant: 'Sweet Dreams Patisserie',
    partnerId: 'sweet-dreams',
    originalPrice: 110,
    discountedPrice: 35,
    distance: '0.5km',
    timeLeft: '5h left',
    image: 'photo-1578985545062-69928b1d9587',
    category: 'Patisserie',
    pickupTime: 'Today 18:30-20:30',
    rating: 4.9,
    description: 'Exquisite handmade desserts and pastries crafted by our master patissier.',
    items: ['Macarons', 'Eclairs', 'Fruit tarts', 'Chocolate cake']
  },
  {
    id: '6',
    title: 'Organic Farm Bundle',
    restaurant: 'Farm to Table',
    partnerId: 'farm-to-table',
    originalPrice: 80,
    discountedPrice: 30,
    distance: '1.5km',
    timeLeft: '6h left',
    image: 'photo-1542838132-92c53300491e',
    category: 'Organic Market',
    pickupTime: 'Today 16:00-19:00',
    rating: 4.8,
    description: 'Fresh organic produce directly from local farms.',
    items: ['Organic vegetables', 'Free-range eggs', 'Raw honey', 'Herbs']
  }
];

const categories = ['All', 'Bakery', 'Café', 'Restaurant', 'Supermarket', 'Patisserie', 'Organic Market'];

const Marketplace = () => {
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [searchQuery, setSearchQuery] = useState('');

  const filteredOffers = mockOffers.filter(offer => {
    const matchesCategory = selectedCategory === 'All' || offer.category === selectedCategory;
    const matchesSearch = offer.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
                         offer.restaurant.toLowerCase().includes(searchQuery.toLowerCase());
    return matchesCategory && matchesSearch;
  });

  const discountPercentage = (original: number, discounted: number) => {
    return Math.round(((original - discounted) / original) * 100);
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white shadow-sm sticky top-0 z-40">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
          <div className="flex items-center justify-between mb-4">
            <h1 className="text-2xl font-bold text-gray-900">Marketplace</h1>
            <Button variant="outline" size="sm">
              <Filter size={16} className="mr-2" />
              Filters
            </Button>
          </div>
          
          {/* Search Bar */}
          <div className="relative mb-4">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
            <Input
              placeholder="Search for restaurants, food types..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-10"
            />
          </div>

          {/* Categories */}
          <div className="flex gap-2 overflow-x-auto pb-2">
            {categories.map((category) => (
              <Badge
                key={category}
                variant={selectedCategory === category ? "default" : "outline"}
                className={`cursor-pointer whitespace-nowrap ${
                  selectedCategory === category 
                    ? 'bg-green-600 hover:bg-green-700' 
                    : 'hover:bg-gray-100'
                }`}
                onClick={() => setSelectedCategory(category)}
              >
                {category}
              </Badge>
            ))}
          </div>
        </div>
      </div>

      {/* Results */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
        <div className="flex items-center justify-between mb-6">
          <p className="text-gray-600">
            {filteredOffers.length} offers available near you
          </p>
          <div className="flex items-center gap-2 text-sm text-gray-500">
            <MapPin size={16} />
            Chisinau, Moldova
          </div>
        </div>

        {/* Offers Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredOffers.map((offer) => (
            <Link key={offer.id} to={`/marketplace/offer/${offer.id}`}>
              <Card className="group cursor-pointer border-0 shadow-md hover:shadow-xl transition-all duration-300 hover:-translate-y-1 overflow-hidden">
                <div className="relative">
                  <img
                    src={`https://images.unsplash.com/${offer.image}?w=400&h=200&fit=crop`}
                    alt={offer.title}
                    className="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-300"
                  />
                  <div className="absolute top-3 left-3 bg-red-500 text-white px-2 py-1 rounded-full text-sm font-bold">
                    -{discountPercentage(offer.originalPrice, offer.discountedPrice)}%
                  </div>
                  <div className="absolute top-3 right-3 bg-white/90 backdrop-blur-sm rounded-full px-2 py-1 flex items-center gap-1">
                    <Star size={14} className="fill-yellow-400 text-yellow-400" />
                    <span className="text-sm font-medium">{offer.rating}</span>
                  </div>
                  <div className="absolute bottom-3 right-3 bg-orange-500 text-white px-3 py-1 rounded-full text-sm font-medium flex items-center gap-1">
                    <Clock size={14} />
                    {offer.timeLeft}
                  </div>
                </div>
                
                <CardContent className="p-4">
                  <div className="flex items-start justify-between mb-2">
                    <div className="flex-1">
                      <h3 className="font-semibold text-gray-900 line-clamp-2 group-hover:text-green-600 transition-colors">
                        {offer.title}
                      </h3>
                      <p className="text-sm text-gray-600 mt-1">{offer.restaurant}</p>
                    </div>
                  </div>

                  <div className="flex items-center gap-4 text-sm text-gray-500 mb-3">
                    <div className="flex items-center gap-1">
                      <MapPin size={14} />
                      {offer.distance}
                    </div>
                    <div className="flex items-center gap-1">
                      <Clock size={14} />
                      {offer.pickupTime}
                    </div>
                  </div>

                  <div className="flex items-center justify-between">
                    <div className="flex items-center gap-2">
                      <span className="text-xl font-bold text-green-600">
                        {offer.discountedPrice} lei
                      </span>
                      <span className="text-sm text-gray-500 line-through">
                        {offer.originalPrice} lei
                      </span>
                    </div>
                    <Badge variant="outline" className="text-xs">
                      {offer.category}
                    </Badge>
                  </div>
                </CardContent>
              </Card>
            </Link>
          ))}
        </div>

        {filteredOffers.length === 0 && (
          <div className="text-center py-12">
            <p className="text-gray-500 text-lg">No offers found matching your criteria.</p>
            <p className="text-gray-400 mt-2">Try adjusting your search or filters.</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default Marketplace;
